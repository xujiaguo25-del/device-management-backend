package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "device_permission", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class DevicePermission extends BaseAudit {
    @Id
    private String permissionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceInfo deviceInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_status_id")
    private Dict domainStatus;

    private String domainGroup;
    private String noDomainReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smartit_status_id")
    private Dict smartItStatus;

    private String noSmartitReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usb_status_id")
    private Dict usbStatus;

    private String usbReason;
    private LocalDate usbExpireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antivirus_status_id")
    private Dict antivirusStatus;

    private String noSymantecReason;
    private String remark;
}