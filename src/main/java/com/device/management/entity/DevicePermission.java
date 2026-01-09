package com.device.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "device_permission")
public class DevicePermission {

    // ========== 必填字段（NOT NULL）==========
    @Id
    @Column(name = "permission_id", length = 50, nullable = false)
    private String permissionId;

    @Column(name = "device_id", length = 50, nullable = false)
    private String deviceId;


    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    // ========== 可选字段（NULLABLE）==========
    @Column(name = "domain_status_id")
    private Long domainStatusId;

    @Column(name = "domain_group", length = 100)
    private String domainGroup;

    @Column(name = "no_domain_reason", columnDefinition = "TEXT")
    private String noDomainReason;

    @Column(name = "smartit_status_id")
    private Long smartitStatusId;

    @Column(name = "no_smartit_reason", columnDefinition = "TEXT")
    private String noSmartitReason;

    @Column(name = "usb_status_id")
    private Long usbStatusId;

    @Column(name = "usb_reason", columnDefinition = "TEXT")
    private String usbReason;

    @Column(name = "usb_expire_date")
    private LocalDate usbExpireDate;

    @Column(name = "antivirus_status_id")
    private Long antivirusStatusId;

    @Column(name = "no_symantec_reason", columnDefinition = "TEXT")
    private String noSymantecReason;

    @Column(name = "remark", columnDefinition = "TEXT")
    private String remark;


    @Column(name = "creater", length = 100)
    private String creater;

    @Column(name = "updater", length = 100)
    private String updater;

}