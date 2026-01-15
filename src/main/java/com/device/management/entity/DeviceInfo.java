package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "device_info", schema = "public")
public class DeviceInfo {
    @Id
    @Column(name = "device_id", nullable = false, length = 50)
    private String deviceId;

    @Column(name = "device_model", length = 100)
    private String deviceModel;

    @Column(name = "computer_name", length = 100)
    private String computerName;

    @Column(name = "login_username", length = 100)
    private String loginUsername;

    @Column(name = "project", length = 100)
    private String project;

    @Column(name = "dev_room", length = 100)
    private String devRoom;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "remark", columnDefinition = "text")
    private String remark;

    @Column(name = "self_confirm_id")
    private Long selfConfirmId;

    @Column(name = "os_id")
    private Long osId;

    @Column(name = "memory_id")
    private Long memoryId;

    @Column(name = "ssd_id")
    private Long ssdId;

    @Column(name = "hdd_id")
    private Long hddId;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "creater", length = 100)
    private String creater;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "updater", length = 100)
    private String updater;
}
