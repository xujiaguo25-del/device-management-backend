package com.device.management.repository;

import com.device.management.entity.DevicePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;  // 添加这个导入！

@Repository
public interface DevicePermissionRepository extends JpaRepository<DevicePermission, String> {
    // 主键现在是String类型（permission_id）

    // 根据设备ID查询
    List<DevicePermission> findByDeviceId(String deviceId);

    // 检查某个权限ID是否存在
    boolean existsByPermissionId(String permissionId);
}