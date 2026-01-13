package com.device.management.service;

import com.device.management.dto.*;
import com.device.management.entity.*;
import com.device.management.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeviceService {
    private final DeviceRepository deviceRepository;

    /**
     * デバイス一覧取得（ページングとフィルタリング対応）
     */
    public Page<DeviceDTO> list(String deviceName, String userId, String userName, String project, String devRoom, int page, int size) {
        // ページ番号調整：1始まりから0始まりに変換
        page = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by("deviceId").ascending());

        // 条件に合致するデバイス一覧を取得
        List<Device> devices = deviceRepository.findByConditions(deviceName, userId, userName, project, devRoom);
        
        // 総レコード数を取得
        Long totalCount = deviceRepository.countByConditions(deviceName, userId, userName, project, devRoom);
        
        if (devices.isEmpty()) {
            return Page.empty(pageable);
        }

        // ページング処理
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), devices.size());
        List<Device> pagedDevices = devices.subList(start, end);

        // 関連データを一括ロード
        List<String> deviceIds = pagedDevices.stream()
                .map(d -> d.getDeviceId().trim())
                .collect(Collectors.toList());

        Map<String, List<DeviceIp>> ipMap = getDeviceIpMap(deviceIds);
        Map<String, List<Monitor>> monitorMap = getDeviceMonitorMap(deviceIds);

        // DTOに変換
        List<DeviceDTO> dtoList = pagedDevices.stream()
                .map(device -> toDTOWithRelations(device, ipMap, monitorMap))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    /**
     * デバイス詳細情報取得
     */
    public DeviceDTO detail(String deviceId) {
        if (!StringUtils.hasText(deviceId)) return null;

        try {
            Device device = deviceRepository.findByDeviceIdWithDicts(deviceId.trim());
            if (device == null) {
                log.error("デバイス詳細が見つかりません: {}", deviceId);
                return null;
            }

            List<String> ids = Collections.singletonList(device.getDeviceId().trim());
            return toDTOWithRelations(device, getDeviceIpMap(ids), getDeviceMonitorMap(ids));
        } catch (Exception e) {
            log.error("デバイス詳細クエリ中にエラーが発生しました: {}", deviceId, e);
            return null;
        }
    }

    /**
     * 単一ユーザーの全デバイスを取得
     */
    public List<DeviceDTO> getDevicesByUserId(String userId) {
        if (!StringUtils.hasText(userId)) return new ArrayList<>();
        Map<String, List<DeviceDTO>> resultMap = getDeviceMapByUserIds(Collections.singletonList(userId.trim()));
        return resultMap.getOrDefault(userId.trim(), new ArrayList<>());
    }

    /**
     * バッチ処理：ユーザーIDリストに対応するデバイスマッピングを取得
     */
    public Map<String, List<DeviceDTO>> getDeviceMapByUserIds(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) return Collections.emptyMap();

        List<Device> allDevices = deviceRepository.findByUserIdsWithDicts(userIds);
        if (allDevices.isEmpty()) return Collections.emptyMap();

        List<String> deviceIds = allDevices.stream()
                .map(d -> d.getDeviceId().trim())
                .collect(Collectors.toList());

        Map<String, List<DeviceIp>> ipMap = getDeviceIpMap(deviceIds);
        Map<String, List<Monitor>> monitorMap = getDeviceMonitorMap(deviceIds);

        return allDevices.stream()
                .map(dev -> toDTOWithRelations(dev, ipMap, monitorMap))
                .collect(Collectors.groupingBy(DeviceDTO::getUserId));
    }

    /**
     * 全ての開発室名を取得（重複排除）
     */
    public List<String> getAllDevRooms() {
        try {
            List<String> devRooms = deviceRepository.findDistinctDevRooms();
            
            // 空値をフィルタリングし、前後の空白をトリム
            return devRooms.stream()
                    .filter(StringUtils::hasText)
                    .map(String::trim)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("開発室一覧取得中にエラーが発生しました", e);
            return Collections.emptyList();
        }
    }

    /**
     * 全てのプロジェクト名を取得（重複排除）
     */
    public List<String> getAllProjects() {
        try {
            List<String> projects = deviceRepository.findDistinctProjects();
            
            // 空値をフィルタリングし、前後の空白をトリム
            return projects.stream()
                    .filter(StringUtils::hasText)
                    .map(String::trim)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("プロジェクト一覧取得中にエラーが発生しました", e);
            return Collections.emptyList();
        }
    }

    /**
     * UserService呼び出し用：デバイス名またはユーザーIDでフィルタリングしてユニークなユーザーIDリストを取得
     */
    public List<String> findUserIdsByCondition(String deviceName, String userId) {
        return deviceRepository.findUserIdsByCondition(deviceName, userId);
    }

    /**
     * デバイスエンティティを関連データを含むDTOに変換（フィールド欠落防止）
     */
    private DeviceDTO toDTOWithRelations(Device device, Map<String, List<DeviceIp>> ipMap, Map<String, List<Monitor>> monitorMap) {
        DeviceDTO dto = toBasicDTO(device);
        String key = device.getDeviceId().trim();

        // IPリストを設定
        List<DeviceIp> ips = ipMap.getOrDefault(key, new ArrayList<>());
        dto.setDeviceIps(ips.stream().map(ip -> {
            DeviceIpDTO ipDto = new DeviceIpDTO();
            ipDto.setIpId(ip.getIpId());
            ipDto.setIpAddress(ip.getIpAddress());
            ipDto.setDeviceId(key); // 関連ID
            ipDto.setCreateTime(ip.getCreateTime());
            ipDto.setCreater(ip.getCreater());
            ipDto.setUpdateTime(ip.getUpdateTime());
            ipDto.setUpdater(ip.getUpdater());
            return ipDto;
        }).collect(Collectors.toList()));

        // モニターリストを設定
        List<Monitor> monitors = monitorMap.getOrDefault(key, new ArrayList<>());
        dto.setMonitors(monitors.stream().map(m -> {
            MonitorDTO mDto = new MonitorDTO();
            mDto.setMonitorId(m.getMonitorId());
            mDto.setMonitorName(m.getMonitorName());
            mDto.setDeviceId(key); // 関連ID
            mDto.setCreateTime(m.getCreateTime());
            mDto.setCreater(m.getCreater());
            mDto.setUpdateTime(m.getUpdateTime());
            mDto.setUpdater(m.getUpdater());
            return mDto;
        }).collect(Collectors.toList()));

        return dto;
    }

    /**
     * デバイスエンティティを基本情報のみのDTOに変換
     */
    private DeviceDTO toBasicDTO(Device device) {
        DeviceDTO dto = DeviceDTO.builder()
                .deviceId(device.getDeviceId().trim())
                .userId(device.getUserId())
                .userInfo(device.getUser() != null ? UserDTO.builder()
                        .userId(device.getUser().getUserId())
                        .deptId(device.getUser().getDeptId())
                        .userName(device.getUser().getUserName())
                        .userType(DictMapper.toDTO(device.getUser().getUserTypeDict()))
                        .createTime(device.getUser().getCreateTime())
                        .creater(device.getUser().getCreater())
                        .updateTime(device.getUser().getUpdateTime())
                        .updater(device.getUser().getUpdater())
                        .build() : null)
                .deviceModel(device.getDeviceModel())
                .computerName(device.getComputerName())
                .loginUsername(device.getLoginUsername())
                .project(device.getProject())
                .devRoom(device.getDevRoom())
                .remark(device.getRemark())
                .osDict(DictMapper.toDTO(device.getOsDict()))
                .osId(device.getOsDict() != null ? device.getOsDict().getDictId() : null)
                .memoryDict(DictMapper.toDTO(device.getMemoryDict()))
                .memoryId(device.getMemoryDict() != null ? device.getMemoryDict().getDictId() : null)
                .ssdDict(DictMapper.toDTO(device.getSsdDict()))
                .ssdId(device.getSsdDict() != null ? device.getSsdDict().getDictId() : null)
                .hddDict(DictMapper.toDTO(device.getHddDict()))
                .hddId(device.getHddDict() != null ? device.getHddDict().getDictId() : null)
                .selfConfirmDict(DictMapper.toDTO(device.getSelfConfirmDict()))
                .createTime(device.getCreateTime())
                .creater(device.getCreater())
                .updateTime(device.getUpdateTime())
                .updater(device.getUpdater())
                .build();

        return dto;
    }

    /**
     * ヘルパーメソッド：デバイスIDリストに対応するIP情報を取得
     */
    private Map<String, List<DeviceIp>> getDeviceIpMap(List<String> deviceIds) {
        if (deviceIds.isEmpty()) return Collections.emptyMap();
        
        List<Object[]> results = deviceRepository.findDeviceIpsByDeviceIds(deviceIds);
        Map<String, List<DeviceIp>> map = new HashMap<>();
        
        for (Object[] row : results) {
            String id = ((String) row[0]).trim();
            map.computeIfAbsent(id, k -> new ArrayList<>()).add((DeviceIp) row[1]);
        }
        return map;
    }

    /**
     * ヘルパーメソッド：デバイスIDリストに対応するモニター情報を取得
     */
    private Map<String, List<Monitor>> getDeviceMonitorMap(List<String> deviceIds) {
        if (deviceIds.isEmpty()) return Collections.emptyMap();
        
        List<Object[]> results = deviceRepository.findMonitorsByDeviceIds(deviceIds);
        Map<String, List<Monitor>> map = new HashMap<>();
        
        for (Object[] row : results) {
            String id = ((String) row[0]).trim();
            map.computeIfAbsent(id, k -> new ArrayList<>()).add((Monitor) row[1]);
        }
        return map;
    }
}