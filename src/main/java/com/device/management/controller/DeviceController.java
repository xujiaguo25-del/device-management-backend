package com.device.management.controller;

import com.device.management.dto.DeviceDTO;
import com.device.management.dto.ApiResponse;
import com.device.management.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    //デバイス一覧、ページングとフィルタリング機能付き
    //アクセス例：GET /api/devices?userId=JS0105&page=1&size=10
    //アクセス例：GET /api/devices?userName=张三
    //アクセス例：GET /api/devices?project=MS
    //アクセス例：GET /api/devices?devRoom=M2-A-01
    //アクセス例：GET /api/devices
    @GetMapping
    public ApiResponse<Page<DeviceDTO>> list(
            @RequestParam(required = false) String deviceName,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) String devRoom,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.success(
                deviceService.list(deviceName, userId, userName, project, devRoom, page, size)
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
            // 全ての重複しない開発室名を取得
    // アクセス例：GET /api/devices/devroom
    @GetMapping("/devroom")
    public ApiResponse<List<String>> getAllDevRooms() {
        return ApiResponse.success(
                deviceService.getAllDevRooms()
        );
    }

    // 全ての重複しないプロジェクト名を取得
    // アクセス例：GET /api/devices/project
    @GetMapping("/project")
    public ApiResponse<List<String>> getAllProjects() {
        return ApiResponse.success(
                deviceService.getAllProjects()
        );
    }
}