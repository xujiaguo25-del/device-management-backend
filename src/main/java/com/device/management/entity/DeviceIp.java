package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "device_ip", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceIp extends BaseAudit {
    @Id
    private Integer ipId;

    @Column(unique = true)
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceInfo deviceInfo;
}