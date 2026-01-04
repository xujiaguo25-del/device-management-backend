package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 機器IPテーブル（機器に関連するIPアドレスを保存）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("device_ip")
@ApiModel(value = "DeviceIp对象", description = "機器IPテーブル（機器に関連するIPアドレスを保存）")
public class DeviceIp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * IP番号（プライマリキー）
     */
    @TableId("ip_id")
    @ApiModelProperty("IP番号（プライマリキー）")
    private Integer ipId;

    /**
     * 機器番号（外部キー）
     */
    @TableField("device_id")
    @ApiModelProperty("機器番号（外部キー）")
    private String deviceId;

    /**
     * IPアドレス（ユニーク）
     */
    @TableField("ip_address")
    @ApiModelProperty("IPアドレス（ユニーク）")
    private String ipAddress;

    /**
     * 作成日時
     */
    @ApiModelProperty("作成日時")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 作成者
     */
    @TableField("creater")
    @ApiModelProperty("作成者")
    private String creater;

    /**
     * 更新日時
     */
    @ApiModelProperty("更新日時")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    @TableField("updater")
    @ApiModelProperty("更新者")
    private String updater;
}
