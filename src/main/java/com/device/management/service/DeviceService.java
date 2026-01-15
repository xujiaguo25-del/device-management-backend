package com.device.management.service;

import com.device.management.dto.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DeviceService {
    /**
     * 导入设备Excel数据
     * @param file Excel文件
     * @return 导入结果
     */
    ApiResponse<String> importDeviceExcel(MultipartFile file);
}
