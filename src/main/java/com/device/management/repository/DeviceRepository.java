package com.device.management.repository;

import com.device.management.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 设备 Repository
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    List<Device> findByDeviceId(String deviceId);

    // 获取所有不重复的开发室名称
    @Query("SELECT DISTINCT d.devRoom FROM Device d WHERE d.devRoom IS NOT NULL AND TRIM(d.devRoom) != '' ORDER BY d.devRoom")
    List<String> findDistinctDevRooms();

    // 获取所有不重复的项目名称
    @Query("SELECT DISTINCT d.project FROM Device d WHERE d.project IS NOT NULL AND TRIM(d.project) != '' ORDER BY d.project")
    List<String> findDistinctProjects();

    // 根据条件查找用户ID列表
    @Query("SELECT DISTINCT d.userId FROM Device d WHERE d.userId IS NOT NULL " +
            "AND (:deviceName IS NULL OR d.computerName LIKE %:deviceName%) " +
            "AND (:userId IS NULL OR d.userId = :userId)")
    List<String> findUserIdsByCondition(
            @Param("deviceName") String deviceName,
            @Param("userId") String userId
    );

    // 根据用户ID列表查询设备（带关联）
    @Query("SELECT d FROM Device d " +
            "LEFT JOIN FETCH d.osDict " +
            "LEFT JOIN FETCH d.memoryDict " +
            "LEFT JOIN FETCH d.ssdDict " +
            "LEFT JOIN FETCH d.hddDict " +
            "LEFT JOIN FETCH d.selfConfirmDict " +
            "WHERE d.userId IN :userIds")
    List<Device> findByUserIdsWithDicts(@Param("userIds") List<String> userIds);

    // 根据设备ID查询设备（带关联）
    @Query("SELECT d FROM Device d " +
            "LEFT JOIN FETCH d.osDict " +
            "LEFT JOIN FETCH d.memoryDict " +
            "LEFT JOIN FETCH d.ssdDict " +
            "LEFT JOIN FETCH d.hddDict " +
            "LEFT JOIN FETCH d.selfConfirmDict " +
            "WHERE TRIM(d.deviceId) = :deviceId")
    Device findByDeviceIdWithDicts(@Param("deviceId") String deviceId);

    // 根据条件分页查询设备
    @Query("SELECT d FROM Device d " +
            "LEFT JOIN FETCH d.user u " +
            "LEFT JOIN FETCH d.osDict " +
            "LEFT JOIN FETCH d.memoryDict " +
            "LEFT JOIN FETCH d.ssdDict " +
            "LEFT JOIN FETCH d.hddDict " +
            "LEFT JOIN FETCH d.selfConfirmDict " +
            "WHERE (:deviceName IS NULL OR d.computerName LIKE %:deviceName%) " +
            "AND (:userId IS NULL OR d.userId = :userId) " +
            "AND (:name IS NULL OR u.name LIKE %:name%) " +
            "AND (:project IS NULL OR d.project LIKE %:project%) " +
            "AND (:devRoom IS NULL OR d.devRoom LIKE %:devRoom%)")
    List<Device> findByConditions(
            @Param("deviceName") String deviceName,
            @Param("userId") String userId,
            @Param("name") String name,
            @Param("project") String project,
            @Param("devRoom") String devRoom
    );

    // 获取总记录数（带条件）
    @Query("SELECT COUNT(d) FROM Device d " +
            "LEFT JOIN d.user u " +
            "WHERE (:deviceName IS NULL OR d.computerName LIKE %:deviceName%) " +
            "AND (:userId IS NULL OR d.userId = :userId) " +
            "AND (:name IS NULL OR u.name LIKE %:name%) " +
            "AND (:project IS NULL OR d.project LIKE %:project%) " +
            "AND (:devRoom IS NULL OR d.devRoom LIKE %:devRoom%)")
    Long countByConditions(
            @Param("deviceName") String deviceName,
            @Param("userId") String userId,
            @Param("name") String name,
            @Param("project") String project,
            @Param("devRoom") String devRoom
    );

    // 根据设备ID列表获取设备IP映射
    @Query("SELECT TRIM(d.device.deviceId) as deviceId, d FROM DeviceIp d WHERE d.device.deviceId IN :deviceIds")
    List<Object[]> findDeviceIpsByDeviceIds(@Param("deviceIds") List<String> deviceIds);

    // 根据设备ID列表获取显示器映射
    @Query("SELECT TRIM(m.device.deviceId) as deviceId, m FROM Monitor m WHERE m.device.deviceId IN :deviceIds")
    List<Object[]> findMonitorsByDeviceIds(@Param("deviceIds") List<String> deviceIds);

}