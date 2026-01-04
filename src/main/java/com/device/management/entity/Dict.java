package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 統合辞書テーブル（辞書タイプと項目を管理）
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("dict")
@ApiModel(value = "Dict对象", description = "統合辞書テーブル（辞書タイプと項目を管理）")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 辞書ID（プライマリキー）
     */
    @ApiModelProperty("辞書ID（プライマリキー）")
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    /**
     * 辞書タイプコード
     */
    @ApiModelProperty("辞書タイプコード")
    @TableField("dict_type_code")
    private String dictTypeCode;

    /**
     * 辞書タイプ名
     */
    @ApiModelProperty("辞書タイプ名")
    @TableField("dict_type_name")
    private String dictTypeName;

    /**
     * 辞書タイプ説明
     */
    @ApiModelProperty("辞書タイプ説明")
    @TableField("dict_type_description")
    private String dictTypeDescription;

    /**
     * 辞書項目名
     */
    @ApiModelProperty("辞書項目名")
    @TableField("dict_item_name")
    private String dictItemName;

    /**
     * ソート番号
     */
    @TableField("sort")
    @ApiModelProperty("ソート番号")
    private Integer sort;

    /**
     * 有効フラグ（0=無効、1=有効）
     */
    @TableField("is_enabled")
    @ApiModelProperty("有効フラグ（0=無効、1=有効）")
    private Short isEnabled;

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
