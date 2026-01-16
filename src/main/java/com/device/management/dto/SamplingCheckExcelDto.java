package com.device.management.dto;

import com.device.management.annotation.ExcelColumn;
import lombok.Data;

@Data
public class SamplingCheckExcelDto {

    @ExcelColumn(index = 0, name = "编号")
    private String rowNo;

    @ExcelColumn(index = 1, name = "工号")
    private String userId;

    @ExcelColumn(index = 2, name = "姓名")
    private String name;

    @ExcelColumn(index = 3, name = "设备编号")
    private String deviceId;

    @ExcelColumn(index = 4, name = "开机认证")
    private String bootAuthentication;

    @ExcelColumn(index = 5, name = "密码屏保")
    private String screenSaverPwd;

    @ExcelColumn(index = 6, name = "安装软件")
    private String installedSoftware;

    @ExcelColumn(index = 7, name = "安全补丁")
    private String securityPatch;

    @ExcelColumn(index = 8, name = "病毒防护")
    private String antivirusProtection;

    @ExcelColumn(index = 9, name = "USB接口")
    private String usbInterface;

    @ExcelColumn(index = 10, name = "处置措施")
    private String disposalMeasures;
}

