package com.device.management.repository;

import com.device.management.entity.DevicePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceUsagePermissionRepository
    extends JpaRepository<DevicePermission, String> {

    // 権限IDで検索
    DevicePermission findByPermissionId(String permissionId);

    // 権限が存在するか確認する
    boolean existsByPermissionId(String permissionId);
}