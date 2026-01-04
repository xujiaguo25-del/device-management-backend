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
 * 機器使用権限テーブル（機器のドメイン、USB等の権限を保存：機器単位管理）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("device_permission")
@ApiModel(value = "DevicePermission对象", description = "機器使用権限テーブル（機器のドメイン、USB等の権限を保存：機器単位管理）")
public class DevicePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 権限番号（プライマリキー）
     */
    @TableId("permission_id")
    @ApiModelProperty("権限番号（プライマリキー）")
    private String permissionId;

    /**
     * 機器番号（外部キー：device_info 関連）
     */
    @TableField("device_id")
    @ApiModelProperty("機器番号（外部キー：device_info 関連）")
    private String deviceId;

    /**
     * ドメイン状態ID（辞書項目：DOMAIN_STATUS 関連）
     */
    @TableField("domain_status_id")
    @ApiModelProperty("ドメイン状態ID（辞書項目：DOMAIN_STATUS 関連）")
    private Long domainStatusId;

    /**
     * ドメイン内グループ名
     */
    @TableField("domain_group")
    @ApiModelProperty("ドメイン内グループ名")
    private String domainGroup;

    /**
     * ドメイン未参加理由
     */
    @ApiModelProperty("ドメイン未参加理由")
    @TableField("no_domain_reason")
    private String noDomainReason;

    /**
     * SmartIT状態ID（辞書項目：SMARTIT_STATUS 関連）
     */
    @TableField("smartit_status_id")
    @ApiModelProperty("SmartIT状態ID（辞書項目：SMARTIT_STATUS 関連）")
    private Long smartitStatusId;

    /**
     * SmartIT未インストール理由
     */
    @TableField("no_smartit_reason")
    @ApiModelProperty("SmartIT未インストール理由")
    private String noSmartitReason;

    /**
     * USB状態ID（辞書項目：USB_STATUS 関連）
     */
    @TableField("usb_status_id")
    @ApiModelProperty("USB状態ID（辞書項目：USB_STATUS 関連）")
    private Long usbStatusId;

    /**
     * USB使用許可理由
     */
    @TableField("usb_reason")
    @ApiModelProperty("USB使用許可理由")
    private String usbReason;

    /**
     * USB使用有効期限
     */
    @ApiModelProperty("USB使用有効期限")
    @TableField("usb_expire_date")
    private LocalDate usbExpireDate;

    /**
     * antivirus状態ID（辞書項目：ANTIVIRUS_STATUS 関連）
     */
    @TableField("antivirus_status_id")
    @ApiModelProperty("antivirus状態ID（辞書項目：ANTIVIRUS_STATUS 関連）")
    private Long antivirusStatusId;

    /**
     * Symantec未導入理由
     */
    @TableField("no_symantec_reason")
    @ApiModelProperty("Symantec未導入理由")
    private String noSymantecReason;

    /**
     * 備考
     */
    @TableField("remark")
    @ApiModelProperty("備考")
    private String remark;

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
