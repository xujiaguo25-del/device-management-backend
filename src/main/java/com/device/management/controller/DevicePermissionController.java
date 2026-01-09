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
            // 1. レコードの存在確認
            if (devicePermissionRepository.existsById(permissionId)) {
                // 2. レコードが存在する場合：削除処理実行
                devicePermissionRepository.deleteById(permissionId);
                System.out.println("削除成功: " + permissionId);
                // 成功レスポンス返却（200 OK）
                return ApiResponse.success("デバイス権限承認の削除に成功しました");
            } else {
                // 3. レコードが存在しない場合：404エラー返却
                System.out.println("レコードが見つかりません: " + permissionId);
                return ApiResponse.error(404, "デバイス権限レコードが存在しません: " + permissionId);
            }

        } catch (Exception e) {
            // 4. 例外発生時：500エラー返却 + エラーログ出力
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