package com.device.management.service.impl;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.DeviceExcelDto;
import com.device.management.entity.DeviceInfo;
import com.device.management.entity.DeviceIp;
import com.device.management.enums.DictEnum;
import com.device.management.repository.DeviceInfoRepository;
import com.device.management.repository.DeviceIpRepository;
import com.device.management.service.DeviceService;
import com.device.management.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private DeviceIpRepository deviceIpRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse<String> importDeviceExcel(MultipartFile file) {
        if (file.isEmpty()) {
            return new ApiResponse<>(400, "文件为空", null);
        }

        try {
            // 使用通用工具类解析Excel
            List<DeviceExcelDto> excelDataList = ExcelUtil.importExcel(file, DeviceExcelDto.class);
            
            saveDeviceData(excelDataList);
            
            return new ApiResponse<>(200, "导入成功，共处理 " + excelDataList.size() + " 条数据", null);
        } catch (Exception e) {
            log.error("Excel解析失败", e);
            return new ApiResponse<>(500, "Excel解析失败: " + e.getMessage(), null);
        }
    }

    private void saveDeviceData(List<DeviceExcelDto> list) {
        for (DeviceExcelDto dto : list) {
            // 1. 保存 DeviceInfo
            // 使用设备编号作为主键，如果为空则跳过
            if (dto.getDeviceId() == null || dto.getDeviceId().isEmpty()) continue;
            
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(dto.getDeviceId());
            deviceInfo.setDeviceModel(dto.getDeviceModel());
            deviceInfo.setComputerName(dto.getComputerName());
            
            // 优先使用 Excel 中的登录用户名，如果没有则使用 姓名
            if (dto.getLoginUsername() != null && !dto.getLoginUsername().isEmpty()) {
                deviceInfo.setLoginUsername(dto.getLoginUsername());
            } else {
                deviceInfo.setLoginUsername(dto.getUserName());
            }
            
            deviceInfo.setUserId(dto.getUserId());
            deviceInfo.setProject(dto.getDepartment()); // 暂时映射
            deviceInfo.setRemark(dto.getRemark());
            
            // 映射字典字段
            deviceInfo.setOsId(mapOsToId(dto.getOs()));
            deviceInfo.setMemoryId(mapMemoryToId(dto.getMemory()));
            deviceInfo.setSsdId(mapDiskToId(dto.getDisk()));
            
            deviceInfo.setCreateTime(LocalDateTime.now());
            deviceInfo.setUpdateTime(LocalDateTime.now());
            deviceInfo.setCreater("SYSTEM");
            deviceInfo.setUpdater("SYSTEM");
            
            deviceInfoRepository.save(deviceInfo);

            // 2. 保存 DeviceIp
            if (dto.getIpAddress() != null && !dto.getIpAddress().isEmpty()) {
                // 处理可能有多个IP换行的情况
                String[] ips = dto.getIpAddress().split("[\\n\\r,;]+");
                for (String ip : ips) {
                    if (ip.trim().isEmpty()) continue;
                    DeviceIp deviceIp = new DeviceIp();
                    deviceIp.setDeviceId(dto.getDeviceId());
                    deviceIp.setIpAddress(ip.trim());
                    deviceIp.setCreateTime(LocalDateTime.now());
                    deviceIp.setUpdateTime(LocalDateTime.now());
                    deviceIp.setCreater("SYSTEM");
                    deviceIp.setUpdater("SYSTEM");
                    deviceIpRepository.save(deviceIp);
                }
            }
        }
    }

    // 辅助映射方法 - 根据 DictEnum 定义
    private Long mapOsToId(String osStr) {
        if (osStr == null) return null;
        String lower = osStr.toLowerCase().replace(" ", "");
        if (lower.contains("win") && lower.contains("10")) return DictEnum.OS_TYPE_WINDOWS_10.getDictId();
        if (lower.contains("win") && lower.contains("11")) return DictEnum.OS_TYPE_WINDOWS_11.getDictId();
        if (lower.contains("mac")) return DictEnum.OS_TYPE_MACOS_VENTURA.getDictId(); 
        if (lower.contains("linux") || lower.contains("ubuntu")) return DictEnum.OS_TYPE_LINUX_UBUNTU_22_04.getDictId();
        return null;
    }

    private Long mapMemoryToId(String memStr) {
        if (memStr == null) return null;
        String val = memStr.replaceAll("[^0-9]", "");
        if ("8".equals(val)) return DictEnum.MEMORY_SIZE_8GB.getDictId();
        if ("16".equals(val)) return DictEnum.MEMORY_SIZE_16GB.getDictId();
        if ("32".equals(val)) return DictEnum.MEMORY_SIZE_32GB.getDictId();
        if ("64".equals(val)) return DictEnum.MEMORY_SIZE_64GB.getDictId();
        return null;
    }

    private Long mapDiskToId(String diskStr) {
        if (diskStr == null) return null;
        String val = diskStr.replaceAll("[^0-9]", "");
        if ("256".equals(val)) return DictEnum.SSD_SIZE_256GB.getDictId();
        if ("512".equals(val)) return DictEnum.SSD_SIZE_512GB.getDictId();
        if ("1000".equals(val) || "1".equals(val)) return DictEnum.SSD_SIZE_1TB.getDictId(); 
        if ("2000".equals(val) || "2".equals(val)) return DictEnum.SSD_SIZE_2TB.getDictId();
        return null;
    }
}
