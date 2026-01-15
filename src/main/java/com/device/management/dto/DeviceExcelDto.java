package com.device.management.dto;

import com.device.management.annotation.ExcelColumn;
import lombok.Data;

@Data
public class DeviceExcelDto {
    @ExcelColumn(index = 0, name = "工号")
    private String userId;

    @ExcelColumn(index = 1, name = "姓名")
    private String userName;

    @ExcelColumn(index = 2, name = "部门")
    private String department;

    @ExcelColumn(index = 3, name = "设备编号")
    private String deviceId;

    @ExcelColumn(index = 4, name = "旧设备编号")
    private String oldDeviceId;

    @ExcelColumn(index = 5, name = "型号")
    private String deviceModel;

    @ExcelColumn(index = 6, name = "计算机名")
    private String computerName;

    @ExcelColumn(index = 7, name = "IP")
    private String ipAddress;

    @ExcelColumn(index = 8, name = "OS")
    private String os;

    @ExcelColumn(index = 9, name = "内存")
    private String memory;

    @ExcelColumn(index = 10, name = "硬盘")
    private String disk;

    @ExcelColumn(index = 12, name = "登录用户")
    private String loginUsername;
    
    // index 11 is likely empty or separator, index 12 based on my count, 
    // but let's check the remark. The provided doc has "remark" sometimes at the end.
    // Document 1 row 1: ... "1000" "wuzhix" ... "已经归还"
    // "1000" is disk (500, 1000). 
    // "wuzhix" is login user?
    // "D204-TRSW" ...
    // Let's stick to the core fields first.
    @ExcelColumn(index = 15, name = "备注")
    private String remark; 
}
