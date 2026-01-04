package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 統合辞書テーブル（辞書タイプと項目を管理）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
public class Dict {

    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    private String dictTypeCode;

    private String dictTypeName;

    private String dictTypeDescription;

    private String dictItemName;

    private Integer sort;

    private Short isEnabled;

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
