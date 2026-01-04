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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * サンプリングチェックテーブル（サンプリングチェック詳細を保存）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("sampling_check")
@ApiModel(value = "SamplingCheck对象", description = "サンプリングチェックテーブル（サンプリングチェック詳細を保存）")
public class SamplingCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * サンプリングチェック番号（プライマリキー）
     */
    @TableId("sampling_id")
    @ApiModelProperty("サンプリングチェック番号（プライマリキー）")
    private String samplingId;

    /**
     * ユーザID（外部キー）
     */
    @TableField("user_id")
    @ApiModelProperty("ユーザID（外部キー）")
    private String userId;

    /**
     * 機器番号（外部キー）
     */
    @TableField("device_id")
    @ApiModelProperty("機器番号（外部キー）")
    private String deviceId;

    /**
     * インストールソフトウェア
     */
    @ApiModelProperty("インストールソフトウェア")
    @TableField("installed_software")
    private Boolean installedSoftware;

    /**
     * 処置措置
     */
    @ApiModelProperty("処置措置")
    @TableField("disposal_measures")
    private String disposalMeasures;

    /**
     * 氏名
     */
    @TableField("name")
    @ApiModelProperty("氏名")
    private String name;

    /**
     * レポート番号
     */
    @TableField("report_id")
    @ApiModelProperty("レポート番号")
    private String reportId;

    /**
     * 更新日
     */
    @ApiModelProperty("更新日")
    @TableField("update_date")
    private LocalDate updateDate;

    /**
     * スクリーンセーバーパスワード状態
     */
    @TableField("screen_saver_pwd")
    @ApiModelProperty("スクリーンセーバーパスワード状態")
    private Boolean screenSaverPwd;

    /**
     * USBインターフェース状態
     */
    @TableField("usb_interface")
    @ApiModelProperty("USBインターフェース状態")
    private Boolean usbInterface;

    /**
     * セキュリティパッチ状態
     */
    @TableField("security_patch")
    @ApiModelProperty("セキュリティパッチ状態")
    private Boolean securityPatch;

    /**
     * ウイルス防護状態ID
     */
    @ApiModelProperty("ウイルス防護状態ID")
    @TableField("antivirus_protection")
    private Boolean antivirusProtection;

    /**
     * 起動認証状態
     */
    @ApiModelProperty("起動認証状態")
    @TableField("boot_authentication")
    private Boolean bootAuthentication;

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
