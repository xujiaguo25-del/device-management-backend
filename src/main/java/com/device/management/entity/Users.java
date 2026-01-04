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
 * ユーザテーブル（ユーザ基本情報を保存）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("users")
@ApiModel(value = "Users对象", description = "ユーザテーブル（ユーザ基本情報を保存）")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ユーザID（プライマリキー）
     */
    @TableId("user_id")
    @ApiModelProperty("ユーザID（プライマリキー）")
    private String userId;

    /**
     * 部署番号
     */
    @TableField("dept_id")
    @ApiModelProperty("部署番号")
    private String deptId;

    /**
     * 氏名
     */
    @TableField("name")
    @ApiModelProperty("氏名")
    private String name;

    /**
     * ユーザタイプID（辞書項目：USER_TYPE 関連）
     */
    @TableField("user_type_id")
    @ApiModelProperty("ユーザタイプID（辞書項目：USER_TYPE 関連）")
    private Long userTypeId;

    /**
     * パスワード（暗号化保存）
     */
    @TableField("password")
    @ApiModelProperty("パスワード（暗号化保存）")
    private String password;

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
