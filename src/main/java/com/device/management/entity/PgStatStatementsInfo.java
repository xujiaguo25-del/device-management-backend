package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
@TableName("pg_stat_statements_info")
public class PgStatStatementsInfo {

    private Long dealloc;

    private LocalDateTime statsReset;
}
