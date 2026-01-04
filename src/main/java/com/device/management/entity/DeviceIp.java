package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 機器IPテーブル（機器に関連するIPアドレスを保存）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("device_ip")
public class DeviceIp {

    @TableId("ip_id")
    private Integer ipId;

    private String deviceId;

    private String ipAddress;

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
