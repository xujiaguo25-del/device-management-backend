package com.device.service;

import com.device.dto.DeviceDTO;
import com.device.entity.Device;
import com.device.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceDTO createDevice(DeviceDTO dto) {
        Device device = new Device();
        device.setDeviceCode(dto.getDeviceCode());
        device.setDeviceName(dto.getDeviceName());
        device.setDeviceType(dto.getDeviceType());
        device.setDescription(dto.getDescription());
        device.setStatus(dto.getStatus() != null ? dto.getStatus() : "offline");
        device.setLocation(dto.getLocation());
        device.setCreatedAt(LocalDateTime.now());

        Device saved = deviceRepository.save(device);
        return convertToDTO(saved);
    }

    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
        return convertToDTO(device);
    }

    public DeviceDTO updateDevice(Long id, DeviceDTO dto) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        if (dto.getDeviceName() != null) device.setDeviceName(dto.getDeviceName());
        if (dto.getDeviceType() != null) device.setDeviceType(dto.getDeviceType());
        if (dto.getDescription() != null) device.setDescription(dto.getDescription());
        if (dto.getStatus() != null) device.setStatus(dto.getStatus());
        if (dto.getLocation() != null) device.setLocation(dto.getLocation());
        device.setUpdatedAt(LocalDateTime.now());

        Device updated = deviceRepository.save(device);
        return convertToDTO(updated);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    private DeviceDTO convertToDTO(Device device) {
        return DeviceDTO.builder()
                .id(device.getId())
                .deviceCode(device.getDeviceCode())
                .deviceName(device.getDeviceName())
                .deviceType(device.getDeviceType())
                .description(device.getDescription())
                .status(device.getStatus())
                .location(device.getLocation())
                .createdAt(device.getCreatedAt())
                .updatedAt(device.getUpdatedAt())
                .build();
    }
}
