package com.device.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class DevicePermissionExcelVo {
    // 设备信息
    private Integer number;           // 编号
    private String deviceNumber;      // 设备编号（带显示器信息）
    private String computerName;      // 电脑名
    private String ipAddress;         // IP地址（可能有多个）

    // 使用者信息
    private String employeeId;        // 工号
    private String employeeName;      // 姓名
    private String departmentCode;    // 部门代码
    private String loginUsername;     // 登录用户名

    // 域信息
    private String domain;            // 域名
    private String domainGroup;       // 域内组名
    private String noDomainReason;    // 不加域理由

    // SmartIT信息
    private String smartitStatus;     // SmartIT状态
    private String noSmartitReason;   // 不安装SmartIT理由

    // USB信息
    private String usbStatus;         // USB状态
    private String usbOpenReason;     // USB开通理由
    private Date useDeadline;         // 使用截止日期

    // 其他
    private String connectionStatus;  // 连接状态
    private String noSymantecReason;  // 无Symantec理由
    private String remark;            // 备注

    // 多显示器情况
    private String monitorInfo;       // 显示器信息（可能多个）
}