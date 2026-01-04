package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "device_info", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceInfo extends BaseAudit {
    @Id
    private String deviceId;

    private String deviceModel;
    private String computerName;
    private String loginUsername;
    private String project;
    private String devRoom;
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "os_id")
    private Dict os;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id")
    private Dict memory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssd_id")
    private Dict ssd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hdd_id")
    private Dict hdd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "self_confirm_id")
    private Dict selfConfirmStatus;
}