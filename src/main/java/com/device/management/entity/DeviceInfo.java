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
 * 機器情報テーブル（機器ハードウェア構成を保存）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("device_info")
@ApiModel(value = "DeviceInfo对象", description = "機器情報テーブル（機器ハードウェア構成を保存）")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 機器番号（プライマリキー）
     */
    @TableId("device_id")
    @ApiModelProperty("機器番号（プライマリキー）")
    private String deviceId;

    /**
     * ホストモデル
     */
    @ApiModelProperty("ホストモデル")
    @TableField("device_model")
    private String deviceModel;

    /**
     * コンピュータ名
     */
    @ApiModelProperty("コンピュータ名")
    @TableField("computer_name")
    private String computerName;

    /**
     * ログインユーザ名
     */
    @ApiModelProperty("ログインユーザ名")
    @TableField("login_username")
    private String loginUsername;

    /**
     * 所属プロジェクト
     */
    @TableField("project")
    @ApiModelProperty("所属プロジェクト")
    private String project;

    /**
     * 所属開発室
     */
    @TableField("dev_room")
    @ApiModelProperty("所属開発室")
    private String devRoom;

    /**
     * ユーザID（所属ユーザ、外部キー）
     */
    @TableField("user_id")
    @ApiModelProperty("ユーザID（所属ユーザ、外部キー）")
    private String userId;

    /**
     * 備考
     */
    @TableField("remark")
    @ApiModelProperty("備考")
    private String remark;

    /**
     * 本人確認ID（辞書項目：CONFIRM_STATUS 関連）
     */
    @TableField("self_confirm_id")
    @ApiModelProperty("本人確認ID（辞書項目：CONFIRM_STATUS 関連）")
    private Long selfConfirmId;

    /**
     * OSID（辞書項目：OS_TYPE 関連）
     */
    @TableField("os_id")
    @ApiModelProperty("OSID（辞書項目：OS_TYPE 関連）")
    private Long osId;

    /**
     * メモリID（辞書項目：MEMORY_SIZE 関連）
     */
    @TableField("memory_id")
    @ApiModelProperty("メモリID（辞書項目：MEMORY_SIZE 関連）")
    private Long memoryId;

    /**
     * SSDID（辞書項目：SSD_SIZE 関連）
     */
    @TableField("ssd_id")
    @ApiModelProperty("SSDID（辞書項目：SSD_SIZE 関連）")
    private Long ssdId;

    /**
     * HDDID（辞書項目：HDD_SIZE 関連）
     */
    @TableField("hdd_id")
    @ApiModelProperty("HDDID（辞書項目：HDD_SIZE 関連）")
    private Long hddId;

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
