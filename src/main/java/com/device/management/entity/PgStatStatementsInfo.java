package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("pg_stat_statements_info")
@ApiModel(value = "PgStatStatementsInfo对象", description = "")
public class PgStatStatementsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("dealloc")
    private Long dealloc;

    @TableField("stats_reset")
    private LocalDateTime statsReset;
}
