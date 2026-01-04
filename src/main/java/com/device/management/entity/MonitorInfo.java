package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "monitor_info", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class MonitorInfo extends BaseAudit {
    @Id
    private Integer monitorId;

    private String monitorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceInfo deviceInfo;
}