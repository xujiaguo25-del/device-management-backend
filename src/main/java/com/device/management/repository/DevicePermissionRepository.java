package com.device.management.repository;

import com.device.management.dto.DevicePermissionExcelVo;
import com.device.management.entity.DeviceInfo;
import com.device.management.entity.DevicePermission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DevicePermissionRepository
        extends JpaRepository<DevicePermission, Long>,
        JpaSpecificationExecutor<DevicePermission>,
        QueryByExampleExecutor<DevicePermission>
{
    DevicePermission findDevicePermissionsByDevice(DeviceInfo device);

    @Query(value = "SELECT NEW com.device.management.dto.DevicePermissionExcelVo(" +
            // 占位符（number字段查询后手动赋值）
            "null, " +
            // 设备信息
            "d.deviceId, " +
            "d.computerName, " +
            // 聚合IP：修复关联字段ip.device → ip.deviceId
            "(SELECT STRING_AGG(ip.ipAddress, ',') FROM DeviceIp ip WHERE ip.deviceId = d.deviceId), " +
            // 使用者信息
            "u.jobNumber, " +
            "u.name, " +
            "u.deptId, " +
            "d.loginUsername, " +
            // 域信息：只查编码（p.domainStatus），不查字典名称
            "p.domainStatus, " +  // 原domainDict.dictItemName → 改为数字编码
            "p.domainGroup, " +
            "p.noDomainReason, " +
            // SmartIT信息：只查编码（p.smartitStatus）
            "p.smartitStatus, " + // 原smartitDict.dictItemName → 改为数字编码
            "p.noSmartitReason, " +
            // USB信息：只查编码（p.usbStatus）
            "p.usbStatus, " +     // 原usbDict.dictItemName → 改为数字编码
            "p.usbReason, " +
            "p.usbExpireDate, " +
            // 其他
            "'正常', " + // 连接状态（示例固定值）
            "p.noSymantecReason, " +
            "p.remark, " +
            // 聚合显示器：修复关联字段m.device → m.deviceId
            "(SELECT STRING_AGG(m.monitorName, ',') FROM MonitorInfo m WHERE m.device = d.deviceId)" +
            ") " +
            "FROM DeviceInfo d " +
            // 关联用户表
            "LEFT JOIN User u ON d.jobNumber = u.jobNumber " +
            // 关联设备权限表：修复p.device → p.deviceId
            "LEFT JOIN DevicePermission p ON d.deviceId = p.device " +
            // 删掉所有Dict表的LEFT JOIN（后续手动映射字典）
            // 分组：去掉字典相关字段，只保留核心字段
            "GROUP BY d.deviceId, d.computerName, u.jobNumber, u.name, u.deptId, d.loginUsername, " +
            "p.domainStatus, p.domainGroup, p.noDomainReason, p.smartitStatus, " +
            "p.noSmartitReason, p.usbStatus, p.usbReason,  p.usbExpireDate, p.noSymantecReason, p.remark")
    List<DevicePermissionExcelVo> findAllDevicePermissionExcel();

    //todo:查询语句
//    @Query(value = "SELECT NEW com.device.management.dto.DevicePermissionExcelVo(
//      ROW_NUMBER() OVER (ORDER BY &createTime ASC) AS number,
//       (select (STRING_AGG(m.monitor_name, E'\n'), E'\n') FROM MonitorInfo m WHERE m.device = d.deviceId )
//       (select (STRING_AGG(di.ip_address, E'\n'), E'\n') FROM DeviceIp di WHERE di.device = d.deviceId )
//    )
//    from users u right join device d on u.job_number = device.job_number
//              left  join device_permission dp on   d.device_id = dp.device_id
//
//    )

}

/*
* user（0.5）
* device（1）
* mon（s） ip（s） devicePer（1）
* */

/*
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
}*/
