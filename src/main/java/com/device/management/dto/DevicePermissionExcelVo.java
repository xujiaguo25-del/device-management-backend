package com.device.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class DevicePermissionExcelVo {

    private Long number; //エクセル番号
    private String deviceId;
    private Object monitorName;
    private String computerName;
    private Object ipAddress;

    private String userId;
    private String name;
    private String deptId;
    private String loginUsername;

    private Long domainStatusId;
    private String domainGroup;
    private String noDomainReason;

    private Long smartitStatusId;
    private String noSmartitReason;

    private Long usbStatusId;
    private String usbReason;
    private LocalDate useExpireDate;

    private Long antivirusStatusId;
    private String noSymantecReason;
    private String remark;

}


//    ==================================================================================================
//    select null,d.device_id,d.computer_name,'ip',u.job_number,u.name,u.dept_id,d.login_username,
//dp.domain_status_id,dp.domain_group,dp.no_domain_reason,dp.smartit_status_id,dp.no_smartit_reason,
//dp.usb_status_id,dp.usb_reason,dp.usb_expire_date,dp.antivirus_status_id,dp.no_symantec_reason,dp.remark
//from  users u right join device_info d on u.job_number = d.job_number
//          left  join device_permission dp on   d.device_id = dp.device_id
//    ==================================================================================================