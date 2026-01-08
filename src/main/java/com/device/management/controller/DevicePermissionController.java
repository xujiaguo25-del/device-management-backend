package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.entity.DevicePermission;
import com.device.management.repository.DevicePermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/device-permissions")
public class DevicePermissionController {

    private final DevicePermissionRepository devicePermissionRepository;

    public DevicePermissionController(DevicePermissionRepository devicePermissionRepository) {
        this.devicePermissionRepository = devicePermissionRepository;
        System.out.println("=== DevicePermissionController 初始化 ===");
        System.out.println("使用数据库表: device_permission");
    }

    /**
     * 分页查询设备权限审批记录
     */
    @GetMapping
    public ApiResponse<List<DevicePermission>> getDevicePermissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String deviceId,
            @RequestParam(required = false) String permissionId) {

        System.out.println("=== 查询设备权限审批列表 ===");

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<DevicePermission> pageResult = devicePermissionRepository.findAll(pageable);

            System.out.println("查询到总记录数: " + pageResult.getTotalElements());

            // 过滤逻辑
            List<DevicePermission> filteredList = pageResult.getContent().stream()
                    .filter(dp -> {
                        if (deviceId != null && !deviceId.isEmpty() &&
                                !deviceId.equals(dp.getDeviceId())) {
                            return false;
                        }
                        if (permissionId != null && !permissionId.isEmpty() &&
                                !permissionId.equals(dp.getPermissionId())) {
                            return false;
                        }
                        return true;
                    })
                    .collect(Collectors.toList());

            return ApiResponse.page(filteredList, pageResult.getTotalElements(), page, size);

        } catch (Exception e) {
            System.err.println("查询设备权限失败: " + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error(500, "查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID查询设备权限详情
     */
    @GetMapping("/{permissionId}")
    public ApiResponse<DevicePermission> getDevicePermissionById(@PathVariable String permissionId) {
        System.out.println("=== 查询设备权限详情 ===");
        System.out.println("权限ID: " + permissionId);

        Optional<DevicePermission> devicePermission = devicePermissionRepository.findById(permissionId);

        if (devicePermission.isPresent()) {
            return ApiResponse.success(devicePermission.get());
        } else {
            return ApiResponse.error(404, "设备权限记录不存在: " + permissionId);
        }
    }

    /**
     * 创建设备权限审批记录
     */
    @PostMapping
    public ApiResponse<DevicePermission> createDevicePermission(@RequestBody DevicePermission request) {
        System.out.println("=== 创建设备权限审批记录 ===");
        System.out.println("请求数据: " + request);

        try {
            // 验证必填字段
            if (request.getPermissionId() == null || request.getPermissionId().trim().isEmpty()) {
                return ApiResponse.error(400, "权限ID不能为空");
            }
            if (request.getDeviceId() == null || request.getDeviceId().trim().isEmpty()) {
                return ApiResponse.error(400, "设备ID不能为空");
            }
            // 注意：已删除 jobNumber 的验证

            // 检查权限ID是否已存在
            if (devicePermissionRepository.existsByPermissionId(request.getPermissionId())) {
                return ApiResponse.error(409, "权限ID已存在: " + request.getPermissionId());
            }

            // 设置时间和默认值
            LocalDateTime now = LocalDateTime.now();
            request.setCreateTime(now);
            request.setUpdateTime(now);
            request.setIsDeleted(0); // 默认未删除

            // 如果没有指定创建人和更新人，可以设置默认值
            if (request.getCreater() == null) {
                request.setCreater("system");
            }
            if (request.getUpdater() == null) {
                request.setUpdater("system");
            }

            // 保存到数据库
            DevicePermission saved = devicePermissionRepository.save(request);

            System.out.println("创建设备权限成功: ID=" + saved.getPermissionId());
            return ApiResponse.success("创建设备权限审批成功", saved);

        } catch (Exception e) {
            System.err.println("创建设备权限异常: " + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error(500, "创建失败: " + e.getMessage());
        }
    }

    /**
     * 更新设备权限审批记录
     */
    @PutMapping("/{permissionId}")
    public ApiResponse<DevicePermission> updateDevicePermission(
            @PathVariable String permissionId,
            @RequestBody DevicePermission request) {

        System.out.println("=== 更新设备权限审批记录 ===");
        System.out.println("权限ID: " + permissionId);

        try {
            Optional<DevicePermission> existingOpt = devicePermissionRepository.findById(permissionId);

            if (existingOpt.isPresent()) {
                DevicePermission existing = existingOpt.get();
                System.out.println("找到现有记录: " + existing.getPermissionId());

                // 更新字段（除了permission_id不能修改）
                if (request.getDeviceId() != null) {
                    existing.setDeviceId(request.getDeviceId());
                }
                // 注意：已删除 jobNumber 的更新逻辑
                if (request.getDomainStatusId() != null) {
                    existing.setDomainStatusId(request.getDomainStatusId());
                }
                if (request.getDomainGroup() != null) {
                    existing.setDomainGroup(request.getDomainGroup());
                }
                if (request.getNoDomainReason() != null) {
                    existing.setNoDomainReason(request.getNoDomainReason());
                }
                if (request.getSmartitStatusId() != null) {
                    existing.setSmartitStatusId(request.getSmartitStatusId());
                }
                if (request.getNoSmartitReason() != null) {
                    existing.setNoSmartitReason(request.getNoSmartitReason());
                }
                if (request.getUsbStatusId() != null) {
                    existing.setUsbStatusId(request.getUsbStatusId());
                }
                if (request.getUsbReason() != null) {
                    existing.setUsbReason(request.getUsbReason());
                }
                if (request.getUsbExpireDate() != null) {
                    existing.setUsbExpireDate(request.getUsbExpireDate());
                }
                if (request.getAntivirusStatusId() != null) {
                    existing.setAntivirusStatusId(request.getAntivirusStatusId());
                }
                if (request.getNoSymantecReason() != null) {
                    existing.setNoSymantecReason(request.getNoSymantecReason());
                }
                if (request.getRemark() != null) {
                    existing.setRemark(request.getRemark());
                }
                if (request.getRemarkText() != null) {
                    existing.setRemarkText(request.getRemarkText());
                }
                if (request.getIsDeleted() != null) {
                    existing.setIsDeleted(request.getIsDeleted());
                }

                // 更新时间和更新人
                existing.setUpdateTime(LocalDateTime.now());
                if (request.getUpdater() != null) {
                    existing.setUpdater(request.getUpdater());
                }

                DevicePermission updated = devicePermissionRepository.save(existing);

                System.out.println("更新设备权限成功: " + updated.getPermissionId());
                return ApiResponse.success("更新设备权限审批成功", updated);
            } else {
                System.out.println("未找到记录: " + permissionId);
                return ApiResponse.error(404, "设备权限记录不存在: " + permissionId);
            }

        } catch (Exception e) {
            System.err.println("更新设备权限异常: " + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error(500, "更新失败: " + e.getMessage());
        }
    }

    /**
     * 删除设备权限审批记录
     */
    @DeleteMapping("/{permissionId}")
    public ApiResponse<Void> deleteDevicePermission(@PathVariable String permissionId) {
        System.out.println("=== 删除设备权限审批记录 ===");
        System.out.println("权限ID: " + permissionId);

        try {
            if (devicePermissionRepository.existsById(permissionId)) {
                devicePermissionRepository.deleteById(permissionId);
                System.out.println("删除成功: " + permissionId);
                return ApiResponse.success("删除设备权限审批成功");
            } else {
                System.out.println("未找到记录: " + permissionId);
                return ApiResponse.error(404, "设备权限记录不存在: " + permissionId);
            }

        } catch (Exception e) {
            System.err.println("删除设备权限异常: " + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error(500, "删除失败: " + e.getMessage());
        }
    }

    /**
     * 根据设备ID查询权限记录
     */
    @GetMapping("/device/{deviceId}")
    public ApiResponse<List<DevicePermission>> getPermissionsByDeviceId(@PathVariable String deviceId) {
        System.out.println("=== 根据设备ID查询权限记录 ===");
        System.out.println("设备ID: " + deviceId);

        try {
            List<DevicePermission> permissions = devicePermissionRepository.findByDeviceId(deviceId);
            System.out.println("查询到记录数: " + permissions.size());
            return ApiResponse.success(permissions);
        } catch (Exception e) {
            System.err.println("查询失败: " + e.getMessage());
            e.printStackTrace();
            return ApiResponse.error(500, "查询失败: " + e.getMessage());
        }
    }
}