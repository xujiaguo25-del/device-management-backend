package com.device.management.service;

import com.device.management.dto.DeviceFullDTO;
import com.device.management.dto.DictDTO;
import com.device.management.entity.Device;
import com.device.management.entity.DeviceIp;
import com.device.management.entity.Monitor;
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
public class DeviceService {

    private final DeviceRepository deviceRepository;

    /**
     * デバイス一覧取得（ページングとフィルタリング対応）
     */
    @Transactional(readOnly = true)
    public Page<DeviceFullDTO> list(String deviceName, String userId, int page, int size) {
        // ページ番号調整：1始まりから0始まりに変換
        page = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by("deviceId").ascending());

        // 条件に合致するデバイス一覧を取得
        List<Device> devices = deviceRepository.findByConditions(deviceName, userId);
        
        // 総レコード数を取得
        Long totalCount = deviceRepository.countByConditions(deviceName, userId);
        
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
        List<DeviceFullDTO> dtoList = pagedDevices.stream()
                .map(device -> toFullDTOWithRelations(device, ipMap, monitorMap))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    /**
     * デバイス詳細情報取得
     */
    @Transactional(readOnly = true)
    public DeviceFullDTO detail(String deviceId) {
        if (!StringUtils.hasText(deviceId)) return null;

        try {
            Device device = deviceRepository.findByDeviceIdWithDicts(deviceId.trim());
            if (device == null) {
                log.error("デバイス詳細が見つかりません: {}", deviceId);
                return null;
            }

            List<String> ids = Collections.singletonList(device.getDeviceId().trim());
            return toFullDTOWithRelations(device, getDeviceIpMap(ids), getDeviceMonitorMap(ids));
        } catch (Exception e) {
            log.error("デバイス詳細クエリ中にエラーが発生しました: {}", deviceId, e);
            return null;
        }
    }

    /**
     * デバイスエンティティを関連データを含むDTOに変換
     */
    private DeviceFullDTO toFullDTOWithRelations(Device device, Map<String, List<DeviceIp>> ipMap, Map<String, List<Monitor>> monitorMap) {
        DeviceFullDTO dto = toFullBasicDTO(device);
        String key = device.getDeviceId().trim();

        // IPリストを設定
        List<DeviceIp> ips = ipMap.getOrDefault(key, new ArrayList<>());
        dto.setDeviceIps(ips);

        // モニターリストを設定
        List<Monitor> monitors = monitorMap.getOrDefault(key, new ArrayList<>());
        dto.setMonitors(monitors);

        return dto;
    }

    /**
     * デバイスエンティティを基本情報のみのDTOに変換
     */
    private DeviceFullDTO toFullBasicDTO(Device device) {
        DeviceFullDTO dto = DeviceFullDTO.builder()
                .deviceId(device.getDeviceId().trim())
                .userId(device.getUserId())
                .name(device.getUser() != null ? device.getUser().getName() : null)
                .deptId(device.getUser() != null ? device.getUser().getDeptId() : null)
                .deviceModel(device.getDeviceModel())
                .computerName(device.getComputerName())
                .loginUsername(device.getLoginUsername())
                .project(device.getProject())
                .devRoom(device.getDevRoom())
                .remark(device.getRemark())
                .selfConfirmDict(DictDTO.fromEntity(device.getSelfConfirmDict()))
                .osDict(DictDTO.fromEntity(device.getOsDict()))
                .memoryDict(DictDTO.fromEntity(device.getMemoryDict()))
                .ssdDict(DictDTO.fromEntity(device.getSsdDict()))
                .hddDict(DictDTO.fromEntity(device.getHddDict()))
                .createTime(device.getCreateTime())
                .creater(device.getCreater())
                .updateTime(device.getUpdateTime())
                .updater(device.getUpdater())
                .monitors(new ArrayList<>())
                .deviceIps(new ArrayList<>())
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