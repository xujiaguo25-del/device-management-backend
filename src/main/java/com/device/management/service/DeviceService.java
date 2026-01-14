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
     * デバイス一覧取得（ページングとフィルタリング対応
     */
    @Transactional(readOnly = true)
    public Page<DeviceFullDTO> list(String deviceName, String userId, int page, int size) {
        try {
            // パラメータ検証
            if (page < 1) {
                throw new AllException(400, "ページ番号は1以上である必要があります");
            }
            if (size < 1 || size > 100) {
                throw new AllException(400, "ページサイズは1から100の間である必要があります");
            }

            // ページ番号調整：1始まりから0始まりに変換
            page = page - 1;
            Pageable pageable = PageRequest.of(page, size, Sort.by("deviceId").ascending());

            Page<Device> devicePage = deviceRepository.findByConditionsWithPagination(
                    deviceName, userId, pageable);

            // デバイスが見つからない場合の処理
            if (!devicePage.hasContent()) {
                String searchCondition = buildSearchConditionMessage(deviceName, userId);
                
                if (StringUtils.hasText(deviceName) || StringUtils.hasText(userId)) {
                    log.warn("指定された条件に一致するデバイスが見つかりません: {}", searchCondition);
                    throw new ResourceNotFoundException("指定された条件に一致するデバイスが見つかりません: " + searchCondition);
                } else {
                    log.info("デバイスが登録されていません");
                    return Page.empty(pageable);
                }
            }

            // 获取当前页的设备ID列表
            List<String> deviceIds = devicePage.getContent().stream()
                    .map(d -> d.getDeviceId().trim())
                    .collect(Collectors.toList());

            // 関連データを一括ロード
            Map<String, List<DeviceIp>> ipMap = getDeviceIpMap(deviceIds);
            Map<String, List<Monitor>> monitorMap = getDeviceMonitorMap(deviceIds);

            // DTOに変換
            List<DeviceFullDTO> dtoList = devicePage.getContent().stream()
                    .map(device -> toFullDTOWithRelations(device, ipMap, monitorMap))
                    .collect(Collectors.toList());

            log.debug("デバイス一覧を取得しました: 件数={}, 総数={}", dtoList.size(), devicePage.getTotalElements());
            return new PageImpl<>(dtoList, pageable, devicePage.getTotalElements());
            
        } catch (AllException | ResourceNotFoundException e) {
            // これらの例外は直接向上抛出
            throw e;
        } catch (Exception e) {
            log.error("デバイス一覧取得中に予期せぬエラーが発生しました", e);
            throw new AllException(500, "デバイス一覧の取得に失敗しました");
        }
    }

    /**
     * 検索条件メッセージを構築
     */
    private String buildSearchConditionMessage(String deviceName, String userId) {
        List<String> conditions = new ArrayList<>();
        if (StringUtils.hasText(deviceName)) {
            conditions.add("デバイス名: " + deviceName);
        }
        if (StringUtils.hasText(userId)) {
            conditions.add("ユーザーID: " + userId);
        }
        return conditions.isEmpty() ? "条件なし" : String.join(", ", conditions);
    }

    /**
     * デバイス詳細情報取得
     */
    @Transactional(readOnly = true)
    public DeviceFullDTO detail(String deviceId) {
        try {
            // パラメータ検証
            if (!StringUtils.hasText(deviceId)) {
                log.warn("デバイスIDが空です");
                throw new AllException(400, "デバイスIDは必須です");
            }

            String trimmedDeviceId = deviceId.trim();
            Device device = deviceRepository.findByDeviceIdWithDicts(trimmedDeviceId);
            
            // デバイスが見つからない場合はResourceNotFoundExceptionをスロー
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
            // これらの例外は直接向上抛出
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