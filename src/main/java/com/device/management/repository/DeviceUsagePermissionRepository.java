package com.device.management.repository;

import com.device.management.entity.DeviceUsagePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceUsagePermissionRepository 
    extends JpaRepository<DeviceUsagePermission, String> {
    
    // 根据权限ID查询
    DeviceUsagePermission findByPermissionId(String permissionId);
    
    // 检查权限是否存在
    boolean existsByPermissionId(String permissionId);
}