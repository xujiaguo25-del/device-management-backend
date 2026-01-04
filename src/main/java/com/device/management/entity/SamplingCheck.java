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
 * サンプリングチェックテーブル（サンプリングチェック詳細を保存）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("sampling_check")
public class SamplingCheck {

    @TableId("sampling_id")
    private String samplingId;

    private String userId;

    private String deviceId;

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

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
