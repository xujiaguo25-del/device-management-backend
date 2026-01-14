package com.device.management.service;

import com.device.management.dto.DeviceFullDTO;
import com.device.management.dto.DictDTO;
import com.device.management.entity.Device;
import com.device.management.entity.DeviceIp;
import com.device.management.entity.Monitor;
import com.device.management.exception.ResourceNotFoundException;
import com.device.management.exception.AllException;
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
        try {
            // 参数验证
            if (page < 1) {
                throw new AllException(400, "ページ番号は1以上である必要があります");
            }
            if (size < 1 || size > 100) {
                throw new AllException(400, "ページサイズは1から100の間である必要があります");
            }

            // ページ番号調整：1始まりから0始まりに変換
            page = page - 1; // page >= 1 已验证
            Pageable pageable = PageRequest.of(page, size, Sort.by("deviceId").ascending());

            // 条件に合致するデバイス一覧を取得
            List<Device> devices = deviceRepository.findByConditions(deviceName, userId);
            
            // 総レコード数を取得
            Long totalCount = deviceRepository.countByConditions(deviceName, userId);
            
            if (devices.isEmpty()) {
                log.info("条件に一致するデバイスが見つかりません: deviceName={}, userId={}", deviceName, userId);
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

            log.debug("デバイス一覧を取得しました: 件数={}, 総数={}", dtoList.size(), totalCount);
            return new PageImpl<>(dtoList, pageable, totalCount);
            
        } catch (AllException e) {
            // AllException 直接向上抛出
            throw e;
        } catch (Exception e) {
            log.error("デバイス一覧取得中に予期せぬエラーが発生しました", e);
            throw new AllException(500, "デバイス一覧の取得に失敗しました");
        }
    }

    /**
     * デバイス詳細情報取得
     */
    @Transactional(readOnly = true)
    public DeviceFullDTO detail(String deviceId) {
        try {
            // 参数验证
            if (!StringUtils.hasText(deviceId)) {
                log.warn("デバイスIDが空です");
                throw new AllException(400, "デバイスIDは必須です");
            }

            String trimmedDeviceId = deviceId.trim();
            Device device = deviceRepository.findByDeviceIdWithDicts(trimmedDeviceId);
            
            if (device == null) {
                log.warn("デバイスが見つかりません: deviceId={}", trimmedDeviceId);
                throw new ResourceNotFoundException("デバイスが見つかりません: " + trimmedDeviceId);
            }

            List<String> ids = Collections.singletonList(device.getDeviceId().trim());
            DeviceFullDTO result = toFullDTOWithRelations(
                device, 
                getDeviceIpMap(ids), 
                getDeviceMonitorMap(ids)
            );
            
            log.debug("デバイス詳細を取得しました: deviceId={}", trimmedDeviceId);
            return result;
            
        } catch (ResourceNotFoundException | AllException e) {
            // 这些异常直接向上抛出
            throw e;
        } catch (Exception e) {
            log.error("デバイス詳細取得中に予期せぬエラーが発生しました: deviceId={}", deviceId, e);
            throw new AllException(500, "デバイス詳細の取得に失敗しました");
        }
    }

    /**
     * デバイスエンティティを関連データを含むDTOに変換
     */
    private DeviceFullDTO toFullDTOWithRelations(Device device, 
            Map<String, List<DeviceIp>> ipMap, 
            Map<String, List<Monitor>> monitorMap) {
        try {
            DeviceFullDTO dto = toFullBasicDTO(device);
            String key = device.getDeviceId().trim();

            // IPリストを設定
            List<DeviceIp> ips = ipMap.getOrDefault(key, new ArrayList<>());
            dto.setDeviceIps(ips);

            // モニターリストを設定
            List<Monitor> monitors = monitorMap.getOrDefault(key, new ArrayList<>());
            dto.setMonitors(monitors);

            return dto;
        } catch (Exception e) {
            log.error("DTO変換中にエラーが発生しました: deviceId={}", device.getDeviceId(), e);
            throw new AllException(500, "データ変換に失敗しました");
        }
    }

    /**
     * デバイスエンティティを基本情報のみのDTOに変換
     */
    private DeviceFullDTO toFullBasicDTO(Device device) {
        try {
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
        } catch (Exception e) {
            log.error("基本DTO変換中にエラーが発生しました: deviceId={}", device.getDeviceId(), e);
            throw new AllException(500, "基本データ変換に失敗しました");
        }
    }

    /**
     * ヘルパーメソッド：デバイスIDリストに対応するIP情報を取得
     */
    private Map<String, List<DeviceIp>> getDeviceIpMap(List<String> deviceIds) {
        try {
            if (deviceIds.isEmpty()) {
                return Collections.emptyMap();
            }
            
            List<Object[]> results = deviceRepository.findDeviceIpsByDeviceIds(deviceIds);
            Map<String, List<DeviceIp>> map = new HashMap<>();
            
            for (Object[] row : results) {
                if (row.length >= 2 && row[0] != null && row[1] != null) {
                    String id = ((String) row[0]).trim();
                    map.computeIfAbsent(id, k -> new ArrayList<>()).add((DeviceIp) row[1]);
                }
            }
            return map;
        } catch (Exception e) {
            log.error("デバイスIP情報取得中にエラーが発生しました", e);
            throw new AllException(500, "IP情報の取得に失敗しました");
        }
    }

    /**
     * ヘルパーメソッド：デバイスIDリストに対応するモニター情報を取得
     */
    private Map<String, List<Monitor>> getDeviceMonitorMap(List<String> deviceIds) {
        try {
            if (deviceIds.isEmpty()) {
                return Collections.emptyMap();
            }
            
            List<Object[]> results = deviceRepository.findMonitorsByDeviceIds(deviceIds);
            Map<String, List<Monitor>> map = new HashMap<>();
            
            for (Object[] row : results) {
                if (row.length >= 2 && row[0] != null && row[1] != null) {
                    String id = ((String) row[0]).trim();
                    map.computeIfAbsent(id, k -> new ArrayList<>()).add((Monitor) row[1]);
                }
            }
            return map;
        } catch (Exception e) {
            log.error("デバイスモニター情報取得中にエラーが発生しました", e);
            throw new AllException(500, "モニター情報の取得に失敗しました");
        }
    }
}