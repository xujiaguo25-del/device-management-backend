package com.device.management.service;

import com.device.management.dto.*;
import com.device.management.entity.*;
import com.device.management.repository.DeviceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PersistenceContext
    private EntityManager entityManager;

    // UserServiceで呼び出し用：デバイス名またはユーザーIDでフィルタリングしてユニークなユーザーIDリストを取得
    public List<String> findUserIdsByCondition(String deviceName, String userId) {
        StringBuilder jpql = new StringBuilder("SELECT DISTINCT d.userId FROM Device d WHERE d.userId IS NOT NULL ");
        if (StringUtils.hasText(deviceName)) {
            jpql.append("AND d.computerName LIKE :deviceName ");
        }
        if (StringUtils.hasText(userId)) {
            jpql.append("AND d.userId = :userId ");
        }

        TypedQuery<String> query = entityManager.createQuery(jpql.toString(), String.class);
        if (StringUtils.hasText(deviceName)) {
            query.setParameter("deviceName", "%" + deviceName + "%");
        }
        if (StringUtils.hasText(userId)) {
            query.setParameter("userId", userId);
        }
        return query.getResultList();
    }

    // デバイス詳細クエリ、CHAR型のTRIM処理
    public DeviceDTO detail(String deviceId) {
        if (!StringUtils.hasText(deviceId)) return null;

        String jpql = "SELECT d FROM Device d " +
                "LEFT JOIN FETCH d.osDict " +
                "LEFT JOIN FETCH d.memoryDict " +
                "LEFT JOIN FETCH d.ssdDict " +
                "LEFT JOIN FETCH d.hddDict " +
                "LEFT JOIN FETCH d.selfConfirmDict " +
                "WHERE TRIM(d.deviceId) = :deviceId";

        try {
            Device device = entityManager.createQuery(jpql, Device.class)
                    .setParameter("deviceId", deviceId.trim())
                    .getSingleResult();

            List<String> ids = Collections.singletonList(device.getDeviceId().trim());
            return toDTOWithRelations(device, getDeviceIpMap(ids), getDeviceMonitorMap(ids));
        } catch (Exception e) {
            log.error("デバイス詳細が見つかりません: {}", deviceId);
            return null;
        }
    }

    // 単一ユーザーの全デバイスを取得
    public List<DeviceDTO> getDevicesByUserId(String userId) {
        if (!StringUtils.hasText(userId)) return new ArrayList<>();
        Map<String, List<DeviceDTO>> resultMap = getDeviceMapByUserIds(Collections.singletonList(userId.trim()));
        return resultMap.getOrDefault(userId.trim(), new ArrayList<>());
    }

    // バッチでユーザーデバイスマッピングを取得
    public Map<String, List<DeviceDTO>> getDeviceMapByUserIds(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) return Collections.emptyMap();

        String jpql = "SELECT d FROM Device d " +
                "LEFT JOIN FETCH d.osDict " +
                "LEFT JOIN FETCH d.memoryDict " +
                "LEFT JOIN FETCH d.ssdDict " +
                "LEFT JOIN FETCH d.hddDict " +
                "LEFT JOIN FETCH d.selfConfirmDict " +
                "WHERE d.userId IN :userIds";

        List<Device> allDevices = entityManager.createQuery(jpql, Device.class)
                .setParameter("userIds", userIds)
                .getResultList();

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

    // フィールド欠落防止
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

        // 集約IP文字列を設定
        dto.setIpAddresses(ips.stream().map(DeviceIp::getIpAddress).collect(Collectors.joining(", ")));

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

    private DeviceDTO toBasicDTO(Device device) {
        return DeviceDTO.builder()
                .deviceId(device.getDeviceId().trim())
                .userId(device.getUserId())
                .userInfo(device.getUserId() != null ? UserDTO.builder()
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
                .osDict(DictMapper.toDTO(device.getOsDict()))
                .memoryDict(DictMapper.toDTO(device.getMemoryDict()))
                .ssdDict(DictMapper.toDTO(device.getSsdDict()))
                .hddDict(DictMapper.toDTO(device.getHddDict()))
                .selfConfirmDict(DictMapper.toDTO(device.getSelfConfirmDict()))
                .createTime(device.getCreateTime())
                .creater(device.getCreater())
                .updateTime(device.getUpdateTime())
                .updater(device.getUpdater())
                .build();
    }

    // プライベートヘルパークエリ、関連データを処理
    private Map<String, List<DeviceIp>> getDeviceIpMap(List<String> deviceIds) {
        if (deviceIds.isEmpty()) return Collections.emptyMap();
        // SQLレイヤーでTRIM処理
        String jpql = "SELECT TRIM(ip.device.deviceId), ip FROM DeviceIp ip WHERE ip.device.deviceId IN :deviceIds";
        List<Object[]> results = entityManager.createQuery(jpql, Object[].class)
                .setParameter("deviceIds", deviceIds)
                .getResultList();
        
        Map<String, List<DeviceIp>> map = new HashMap<>();
        for (Object[] row : results) {
            String id = ((String) row[0]).trim();
            map.computeIfAbsent(id, k -> new ArrayList<>()).add((DeviceIp) row[1]);
        }
        return map;
    }

    private Map<String, List<Monitor>> getDeviceMonitorMap(List<String> deviceIds) {
        if (deviceIds.isEmpty()) return Collections.emptyMap();
        String jpql = "SELECT TRIM(m.device.deviceId), m FROM Monitor m WHERE m.device.deviceId IN :deviceIds";
        List<Object[]> results = entityManager.createQuery(jpql, Object[].class)
                .setParameter("deviceIds", deviceIds)
                .getResultList();
        
        Map<String, List<Monitor>> map = new HashMap<>();
        for (Object[] row : results) {
            String id = ((String) row[0]).trim();
            map.computeIfAbsent(id, k -> new ArrayList<>()).add((Monitor) row[1]);
        }
        return map;
    }
}