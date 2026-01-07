package com.device.management.controller;

import com.device.management.dto.DeviceDTO;
import com.device.management.dto.ApiResponse;
import com.device.management.dto.UserDTO;
import com.device.management.service.DeviceService;
import com.device.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final UserService userService;

    //デバイス一覧、ページングとフィルタリング機能付き
    //アクセス例：GET /api/devices?userId=JS0105&page=0&size=10
    //アクセス例：GET /api/devices
    @GetMapping
    public ApiResponse<Page<UserDTO>> list(
            @RequestParam(required = false) String deviceName,
            @RequestParam(required = false) String userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.success(
                userService.list(deviceName, userId, page, size)
        );
    }

    //デバイス詳細
    //アクセス例：GET /api/devices/JS0105
    @GetMapping("/{deviceId}")
    public ApiResponse<DeviceDTO> detail(
            @PathVariable String deviceId
    ) {
        return ApiResponse.success(
                deviceService.detail(deviceId)
        );
    }
}