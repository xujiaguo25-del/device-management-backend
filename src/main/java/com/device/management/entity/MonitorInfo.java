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
 * モニター情報テーブル（機器に関連するモニターを保存）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("monitor_info")
@ApiModel(value = "MonitorInfo对象", description = "モニター情報テーブル（機器に関連するモニターを保存）")
public class MonitorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * モニター番号（プライマリキー）
     */
    @TableId("monitor_id")
    @ApiModelProperty("モニター番号（プライマリキー）")
    private Integer monitorId;

    /**
     * 機器番号（外部キー）
     */
    @TableField("device_id")
    @ApiModelProperty("機器番号（外部キー）")
    private String deviceId;

    /**
     * モニター名
     */
    @ApiModelProperty("モニター名")
    @TableField("monitor_name")
    private String monitorName;

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
