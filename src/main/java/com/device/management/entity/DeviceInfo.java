package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 機器情報テーブル（機器ハードウェア構成を保存）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("device_info")
public class DeviceInfo {

    @TableId("device_id")
    private String deviceId;

    private String deviceModel;

    private String computerName;

    private String loginUsername;

    private String project;

    private String devRoom;

    private String userId;

    private String remark;

    private Long selfConfirmId;

    private Long osId;

    private Long memoryId;

    private Long ssdId;

    private Long hddId;

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
