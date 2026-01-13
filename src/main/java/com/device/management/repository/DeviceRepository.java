package com.device.management.repository;

import com.device.management.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    // 条件検索（デバイス名、ユーザーID）
    @Query("SELECT d FROM Device d " +
            "LEFT JOIN FETCH d.user u " +
            "LEFT JOIN FETCH d.osDict " +
            "LEFT JOIN FETCH d.memoryDict " +
            "LEFT JOIN FETCH d.ssdDict " +
            "LEFT JOIN FETCH d.hddDict " +
            "LEFT JOIN FETCH d.selfConfirmDict " +
            "WHERE (:deviceName IS NULL OR d.computerName LIKE %:deviceName%) " +
            "AND (:userId IS NULL OR d.userId = :userId)")
    List<Device> findByConditions(
            @Param("deviceName") String deviceName,
            @Param("userId") String userId
    );

    // 条件検索の総レコード数
    @Query("SELECT COUNT(d) FROM Device d " +
            "LEFT JOIN d.user u " +
            "WHERE (:deviceName IS NULL OR d.computerName LIKE %:deviceName%) " +
            "AND (:userId IS NULL OR d.userId = :userId)")
    Long countByConditions(
            @Param("deviceName") String deviceName,
            @Param("userId") String userId
    );

    // デバイス詳細（辞書情報を含む）
    @Query("SELECT d FROM Device d " +
            "LEFT JOIN FETCH d.osDict " +
            "LEFT JOIN FETCH d.memoryDict " +
            "LEFT JOIN FETCH d.ssdDict " +
            "LEFT JOIN FETCH d.hddDict " +
            "LEFT JOIN FETCH d.selfConfirmDict " +
            "WHERE TRIM(d.deviceId) = :deviceId")
    Device findByDeviceIdWithDicts(@Param("deviceId") String deviceId);

    // デバイスIP情報のバッチ取得
    @Query("SELECT TRIM(d.device.deviceId) as deviceId, d FROM DeviceIp d WHERE d.device.deviceId IN :deviceIds")
    List<Object[]> findDeviceIpsByDeviceIds(@Param("deviceIds") List<String> deviceIds);

    // モニター情報のバッチ取得
    @Query("SELECT TRIM(m.device.deviceId) as deviceId, m FROM Monitor m WHERE m.device.deviceId IN :deviceIds")
    List<Object[]> findMonitorsByDeviceIds(@Param("deviceIds") List<String> deviceIds);
}