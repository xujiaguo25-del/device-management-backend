package com.device.management.repository;

import com.device.management.entity.DeviceUsagePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *デバイス使用権限リポジトリインターフェース
 */
public interface DeviceUsagePermissionRepository extends
        // 基本的なCRUD操作
        JpaRepository<DeviceUsagePermission, String>,
        //動的クエリ操作
        JpaSpecificationExecutor<DeviceUsagePermission>
{
}