package com.device.management.service;

import com.device.management.dto.PermissionInsertDTO;
import com.device.management.entity.DeviceInfo;
import com.device.management.entity.DevicePermission;
import com.device.management.entity.Dict;
import com.device.management.exception.BusinessException;
import com.device.management.repository.DevicePermissionRepository;
import com.device.management.repository.DeviceRepository;
import com.device.management.repository.DictRepository;
import com.device.management.security.JwtTokenProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class DevicePermissionService {
    @Resource
    DevicePermissionRepository devicePermissionRepository;
    @Resource
    DeviceRepository deviceRepository;
    @Resource
    private JwtTokenProvider jwtTokenProvider;
    @Resource
    private DictRepository dictRepository;

    public PermissionInsertDTO addPermissions(PermissionInsertDTO permissionsDTO) {


        DeviceInfo deviceInfo = deviceRepository.findByDeviceId(permissionsDTO.getDeviceId());
        if (deviceInfo == null) {
            throw new BusinessException(30002, "デバイスが存在しません");
        }

        DevicePermission devicePermissions = devicePermissionRepository.findDevicePermissionsByDevice(deviceInfo);

        if (devicePermissions != null) {
            throw new BusinessException(30003, "デバイスにはすでに権限情報があります");
        }

        devicePermissionRepository.save(DevicePermission.builder().permissionId(UUID.randomUUID().toString()).device(deviceInfo).domainStatus(Dict.builder().id(permissionsDTO.getDomainStatus()).build()).domainGroup(permissionsDTO.getDomainGroup()).noDomainReason(permissionsDTO.getNoDomainReason()).smartitStatus(Dict.builder().id(permissionsDTO.getSmartitStatus()).build()).noSmartitReason(permissionsDTO.getNoSmartitReason()).usbStatus(Dict.builder().id(permissionsDTO.getUsbStatus()).build()).usbReason(permissionsDTO.getUsbReason()).usbExpireDate(permissionsDTO.getUsbExpireDate()).antivirusStatus(Dict.builder().id(permissionsDTO.getAntivirusStatus()).build()).noSymantecReason(permissionsDTO.getNoSymantecReason()).remark(permissionsDTO.getRemark()).createTime(Instant.now())
//                        .creater(jwtTokenProvider.getUserIdFromToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJKUzIxMTUiLCJzdWIiOiJKUzIxMTUiLCJpYXQiOjE3Njc1OTE4NzgsImV4cCI6MTc2NzY3ODI3OH0.FV_jjUTSWvYEeTYgFtb2iPkalIz48NK_2lTgi-HtWVk"))
                .creater("JS2115").updateTime(Instant.now())
//                        .updater(jwtTokenProvider.getUserIdFromToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJKUzIxMTUiLCJzdWIiOiJKUzIxMTUiLCJpYXQiOjE3Njc1OTE4NzgsImV4cCI6MTc2NzY3ODI3OH0.FV_jjUTSWvYEeTYgFtb2iPkalIz48NK_2lTgi-HtWVk"))
                .updater("JS2115").build());

        return permissionsDTO;
    }
}