package com.device.management.service.impl;

import com.device.management.entity.DeviceUsagePermission;
import com.device.management.exception.ConflictException;
import com.device.management.exception.ResourceNotFoundException;
import com.device.management.repository.DeviceUsagePermissionRepository;
import com.device.management.service.DeviceUsagePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceUsagePermissionServiceImpl implements DeviceUsagePermissionService {

    @Autowired
    private DeviceUsagePermissionRepository permissionRepository;

    @Override
    @Transactional
    public void deletePermissionById(Long id) {
        System.out.println("开始删除设备使用权限: id=" + id);

        // 1. 将API的Long ID映射到数据库的String permissionId
        String permissionId = String.valueOf(id);

        // 2. 检查权限是否存在
        DeviceUsagePermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("权限不存在: " + id));

        // 3. 检查是否已逻辑删除
        if (permission.getIsDeleted() != null && permission.getIsDeleted() == 1) {
            throw new ResourceNotFoundException("权限已被删除: " + id);
        }

        // 4. TODO: 检查关联资源（需要查询其他表）
        // 模拟：如果权限ID包含"TEST"，则视为有关联
        if (permissionId.contains("TEST")) {
            throw new ConflictException("权限已绑定资源，无法删除: " + id);
        }

        // 5. 执行物理删除
        permissionRepository.delete(permission);

        System.out.println("权限物理删除成功: id=" + id + ", permissionId=" + permissionId);
    }
}