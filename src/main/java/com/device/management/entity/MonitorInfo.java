package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * モニター情報テーブル（機器に関連するモニターを保存）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("monitor_info")
public class MonitorInfo {

    @TableId("monitor_id")
    private Integer monitorId;

    private String deviceId;

    private String monitorName;

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
