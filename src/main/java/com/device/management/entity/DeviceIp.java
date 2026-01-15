package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "device_ip", schema = "public")
public class DeviceIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ip_id", nullable = false)
    private Integer ipId;

    @Column(name = "device_id", nullable = false, length = 50)
    private String deviceId;

    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "creater", length = 100)
    private String creater;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "updater", length = 100)
    private String updater;
}
