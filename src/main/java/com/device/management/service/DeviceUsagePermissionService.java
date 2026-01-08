package com.device.management.service;

public interface DeviceUsagePermissionService {
    
    /**
     * 根据ID删除设备使用权限
     * @param id 权限ID（API层的Long类型）
     */
    void deletePermissionById(Long id);
}