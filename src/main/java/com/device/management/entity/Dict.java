package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "dict", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class Dict extends BaseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dict_id_seq")
    @SequenceGenerator(name = "dict_id_seq", sequenceName = "dict_dict_id_seq", allocationSize = 1)
    private Long dictId;

    private String dictTypeCode;
    private String dictTypeName;
    private String dictTypeDescription;
    private String dictItemName;
    private Integer sort;
    private Short isEnabled; // 0=無効、1=有効
}