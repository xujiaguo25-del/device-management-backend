package com.device.controller;

import com.device.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/hello")
    public ApiResponse<String> hello() {
        return ApiResponse.success("Hello from Device Management Backend!");
    }

    @GetMapping("/users")
    public ApiResponse<List<Map<String, Object>>> getUsers() {
        return ApiResponse.success(Arrays.asList(
                Map.of(
                        "id", 1,
                        "name", "张三",
                        "email", "zhangsan@example.com",
                        "role", "ADMIN"
                ),
                Map.of(
                        "id", 2,
                        "name", "李四",
                        "email", "lisi@example.com",
                        "role", "USER"
                ),
                Map.of(
                        "id", 3,
                        "name", "王五",
                        "email", "wangwu@example.com",
                        "role", "USER"
                )
        ));
    }

    @PostMapping("/echo")
    public ApiResponse<Map<String, Object>> echo(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("received", request);
        response.put("timestamp", System.currentTimeMillis());
        return ApiResponse.success(response);
    }

    @GetMapping("/stats")
    public ApiResponse<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalDevices", 152);
        stats.put("onlineDevices", 128);
        stats.put("offlineDevices", 24);
        stats.put("totalUsers", 45);
        stats.put("totalOrganizations", 8);
        stats.put("uptime", "99.9%");
        return ApiResponse.success(stats);
    }

    @GetMapping("/devices-sample")
    public ApiResponse<List<Map<String, Object>>> getDevicesSample() {
        return ApiResponse.success(Arrays.asList(
                Map.of(
                        "id", 1,
                        "deviceCode", "DEV-001",
                        "deviceName", "温度传感器1",
                        "deviceType", "Temperature Sensor",
                        "status", "online",
                        "location", "仓库A"
                ),
                Map.of(
                        "id", 2,
                        "deviceCode", "DEV-002",
                        "deviceName", "湿度传感器1",
                        "deviceType", "Humidity Sensor",
                        "status", "online",
                        "location", "仓库B"
                ),
                Map.of(
                        "id", 3,
                        "deviceCode", "DEV-003",
                        "deviceName", "摄像头1",
                        "deviceType", "Camera",
                        "status", "offline",
                        "location", "大厅"
                )
        ));
    }
}
