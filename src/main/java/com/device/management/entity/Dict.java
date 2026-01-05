package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dict")
public class Dict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_id", columnDefinition = "bigint")
    private Long dictId; // 辞書ID(プライマリキー)

    @Column(name = "dict_type_code", nullable = false, length = 60)
    private String dictTypeCode; // 辞書タイプコード

    @Column(name = "dict_type_name", nullable = false, length = 100)
    private String dictTypeName; // 辞書タイプ名

    @Column(name = "dict_type_description", columnDefinition = "text")
    private String dictTypeDescription; // 辞書タイプ説明

    @Column(name = "dict_item_name", nullable = false, length = 100)
    private String dictItemName; // 辞書項目名

    @Column(name = "sort")
    private Integer sort; // ソート番号

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = true; // 有効フラグ（0=無効、1=有効）

    @Column(name = "create_time", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createTime; // 作成日時

    @Column(name = "creater", length = 100)
    private String creater; // 作成者

    @Column(name = "update_time", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateTime; // 更新日時

    @Column(name = "updater", length = 100)
    private String updater; // 更新者

}