
package com.deviceManagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "dict")
@Data
public class Dict {
    @Id
    @Column(name = "dict_id")
    private Long dictId; // 主键

    @Column(name = "dict_type_code", nullable = false)
    private String dictTypeCode; // 字典类型编码

    @Column(name = "dict_type_name", nullable = false)
    private String dictTypeName; // 字典类型名称

    @Column(name = "dict_type_description")
    private String dictTypeDescription; // 字典类型描述

    @Column(name = "dict_item_name", nullable = false)
    private String dictItemName; // 字典项目名

    @Column(name = "sort", columnDefinition = "int4 default 1")
    private Integer sort; // 排序号

    @Column(name = "is_enabled", nullable = false, columnDefinition = "int2 default 1")
    private Short isEnabled; // 有效标志

    @Column(name = "create_time", nullable = false, columnDefinition = "timestamp(6) default CURRENT_TIMESTAMP")
    private LocalDateTime createTime; // 创建时间

    @Column(name = "creater")
    private String creater; // 创建者

    @Column(name = "update_time", nullable = false, columnDefinition = "timestamp(6) default CURRENT_TIMESTAMP")
    private LocalDateTime updateTime; // 更新时间

    @Column(name = "updater")
    private String updater; // 更新者
}