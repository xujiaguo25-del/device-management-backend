package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 機器使用権限テーブル（機器のドメイン、USB等の権限を保存：機器単位管理）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("device_permission")
public class DevicePermission {

    @TableId("permission_id")
    private String permissionId;

    private String deviceId;

    private Long domainStatusId;

    private String domainGroup;

    private String noDomainReason;

    private Long smartitStatusId;

    private String noSmartitReason;

    private Long usbStatusId;

    private String usbReason;

    private LocalDate usbExpireDate;

    private Long antivirusStatusId;

    private String noSymantecReason;

    private String remark;

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
