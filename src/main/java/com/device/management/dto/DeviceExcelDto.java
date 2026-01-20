package com.device.management.dto;

import com.device.management.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DeviceExcelDto {
    @ExcelColumn(index = 0, name = "工号")
    private String userId;

    @ExcelColumn(index = 1, name = "姓名")
    private String userName;

    @ExcelColumn(index = 2, name = "部门")
    private String department;

    @ExcelColumn(index = 3, name = "主机设备编号")
    private String deviceId;

    @ExcelColumn(index = 4, name = "显示器设备编号")
    private String monitorDeviceId;

    @ExcelColumn(index = 5, name = "主机型号")
    private String deviceModel;

    @ExcelColumn(index = 6, name = "电脑名")
    private String computerName;

    @ExcelColumn(index = 7, name = "IP地址")
    private String ipAddress;

    @ExcelColumn(index = 8, name = "操作系统")
    private String os;

    @ExcelColumn(index = 9, name = "内存单位(G)")
    private String memory;

    @ExcelColumn(index = 10, name = "固态硬盘单位(G)")
    private String ssd;

    @ExcelColumn(index = 11, name = "机械硬盘")
    private String hdd;

    @ExcelColumn(index = 12, name = "登录用户名")
    private String loginUsername;

    @ExcelColumn(index = 13, name = "所在项目")
    private String project;

    @ExcelColumn(index = 14, name = "所在开发室")
    private String devRoom;

    @ExcelColumn(index = 15, name = "备注")
    private String remark;

    @ExcelColumn(index = 16, name = "本人确认")
    private String selfConfirm;
}
