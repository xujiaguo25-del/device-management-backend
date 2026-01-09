package com.device.management.repository;

import com.device.management.entity.DevicePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DevicePermissionRepository extends JpaRepository<DevicePermission, String> {

    // 根据设备ID查询
    List<DevicePermission> findByDeviceId(String deviceId);

    // 检查某个权限ID是否存在
    boolean existsByPermissionId(String permissionId);

    // 新增：根据permission_id查询单个权限
    @Query("SELECT dp FROM DevicePermission dp WHERE dp.permissionId = :permissionId")
    DevicePermission findByPermissionId(@Param("permissionId") String permissionId);

    // 新增：根据permission_id删除（需要@Modifying和@Transactional）
    @Modifying
    @Transactional
    @Query("DELETE FROM DevicePermission dp WHERE dp.permissionId = :permissionId")
    int deleteByPermissionId(@Param("permissionId") String permissionId);
}