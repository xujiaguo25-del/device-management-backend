package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.entity.DevicePermission;
import com.device.management.repository.DevicePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DevicePermissionController {

    private final DevicePermissionRepository devicePermissionRepository;

    @Autowired
    public DevicePermissionController(DevicePermissionRepository devicePermissionRepository) {
        this.devicePermissionRepository = devicePermissionRepository;
    }

    /**
     * 元の削除ロジックを保持 - パス: /device-permissions/{permissionId}
     */
    @DeleteMapping("/device-permissions/{permissionId}")
    public ApiResponse<Void> deleteDevicePermission(@PathVariable String permissionId) {
        System.out.println("=== デバイス権限承認レコードを削除 ===");
        System.out.println("権限ID: " + permissionId);

        try {
            if (devicePermissionRepository.existsById(permissionId)) {
                devicePermissionRepository.deleteById(permissionId);
                System.out.println("削除成功: " + permissionId);
                return ApiResponse.success("デバイス権限承認の削除に成功しました");
            } else {
                System.out.println("レコードが見つかりません: " + permissionId);
                return ApiResponse.error(404, "デバイス権限レコードが存在しません: " + permissionId);
            }

        } catch (Exception e) {
            System.err.println("デバイス権限の削除時に例外が発生しました: " + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error(500, "削除に失敗しました: " + e.getMessage());
        }
    }

    /**
     * OASドキュメントで指定された削除インターフェース - パス: /permissions/{id}
     * 元の削除ロジックを直接呼び出す
     */
    @DeleteMapping("/permissions/{id}")
    public ApiResponse<Void> deletePermission(@PathVariable("id") String permissionId) {
        System.out.println("=== OASドキュメント削除インターフェースを実行 ===");
        return deleteDevicePermission(permissionId);
    }
}