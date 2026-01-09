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

    //デバイスIDで検索
    List<DevicePermission> findByDeviceId(String deviceId);

    // 特定の権限IDが存在するか確認する
    boolean existsByPermissionId(String permissionId);

    //permission_idに基づいて単一の権限を検索する
    @Query("SELECT dp FROM DevicePermission dp WHERE dp.permissionId = :permissionId")
    DevicePermission findByPermissionId(@Param("permissionId") String permissionId);

    // permission_idに基づいて削除（@Modifyingと@Transactionalが必要）
    @Modifying
    @Transactional
    @Query("DELETE FROM DevicePermission dp WHERE dp.permissionId = :permissionId")
    int deleteByPermissionId(@Param("permissionId") String permissionId);
}