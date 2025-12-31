package com.device.controller;

import com.device.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Device Management API");
        response.put("version", "1.0.0");
        return ApiResponse.success(response);
    }

    @GetMapping("/info")
    public ApiResponse<Map<String, String>> getInfo() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "Device Management System");
        response.put("description", "后端设备管理系统API");
        response.put("version", "1.0.0");
        response.put("environment", "production");
        return ApiResponse.success(response);
    }
}
