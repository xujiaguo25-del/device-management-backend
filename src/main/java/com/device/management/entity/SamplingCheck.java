package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "sampling_check", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class SamplingCheck extends BaseAudit {
    @Id
    private String samplingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceInfo deviceInfo;

    private Boolean installedSoftware;
    private String disposalMeasures;
    private String name;
    private String reportId;
    private LocalDate updateDate;
    private Boolean screenSaverPwd;
    private Boolean usbInterface;
    private Boolean securityPatch;
    private Boolean antivirusProtection;
    private Boolean bootAuthentication;
}