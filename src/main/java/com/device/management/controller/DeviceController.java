package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.DeviceFullDTO;
import com.device.management.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * デバイス管理コントローラー
 * 
 * エンドポイント:
 * - GET /devices: デバイス一覧取得（ページングとフィルタリング対応）
 * - GET /devices/{deviceId}: デバイス詳細情報取得
 */
@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * デバイス一覧取得（ページングとフィルタリング対応） 
     * アクセス例：GET /api/devices?userId=JS0105&page=1&size=10
     */
    @GetMapping
    public ApiResponse<Page<DeviceFullDTO>> list(
            @RequestParam(required = false) String deviceName,
            @RequestParam(required = false) String userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // Serviceを呼び出し、例外はGlobalExceptionHandlerが自動的に処理
        Page<DeviceFullDTO> result = deviceService.list(deviceName, userId, page, size);
        return ApiResponse.success(result);
    }

    /**
     * デバイス詳細情報取得
     * アクセス例：GET /api/devices/deviceId
     */
    @GetMapping("/{deviceId}")
    public ApiResponse<DeviceFullDTO> detail(
            @PathVariable String deviceId
    ) {
        // Serviceを呼び出し、例外はGlobalExceptionHandlerが自動的に処理
        DeviceFullDTO result = deviceService.detail(deviceId);
        return ApiResponse.success(result);
    }
}