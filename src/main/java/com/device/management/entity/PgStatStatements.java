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
@TableName("pg_stat_statements")
@ApiModel(value = "PgStatStatements对象", description = "")
public class PgStatStatements implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userid")
    private Long userid;

    @TableField("dbid")
    private Long dbid;

    @TableField("toplevel")
    private Boolean toplevel;

    @TableField("queryid")
    private Long queryid;

    @TableField("query")
    private String query;

    @TableField("plans")
    private Long plans;

    @TableField("total_plan_time")
    private Double totalPlanTime;

    @TableField("min_plan_time")
    private Double minPlanTime;

    @TableField("max_plan_time")
    private Double maxPlanTime;

    @TableField("mean_plan_time")
    private Double meanPlanTime;

    @TableField("stddev_plan_time")
    private Double stddevPlanTime;

    @TableField("calls")
    private Long calls;

    @TableField("total_exec_time")
    private Double totalExecTime;

    @TableField("min_exec_time")
    private Double minExecTime;

    @TableField("max_exec_time")
    private Double maxExecTime;

    @TableField("mean_exec_time")
    private Double meanExecTime;

    @TableField("stddev_exec_time")
    private Double stddevExecTime;

    @TableField("rows")
    private Long rows;

    @TableField("shared_blks_hit")
    private Long sharedBlksHit;

    @TableField("shared_blks_read")
    private Long sharedBlksRead;

    @TableField("shared_blks_dirtied")
    private Long sharedBlksDirtied;

    @TableField("shared_blks_written")
    private Long sharedBlksWritten;

    @TableField("local_blks_hit")
    private Long localBlksHit;

    @TableField("local_blks_read")
    private Long localBlksRead;

    @TableField("local_blks_dirtied")
    private Long localBlksDirtied;

    @TableField("local_blks_written")
    private Long localBlksWritten;

    @TableField("temp_blks_read")
    private Long tempBlksRead;

    @TableField("temp_blks_written")
    private Long tempBlksWritten;

    @TableField("shared_blk_read_time")
    private Double sharedBlkReadTime;

    @TableField("shared_blk_write_time")
    private Double sharedBlkWriteTime;

    @TableField("local_blk_read_time")
    private Double localBlkReadTime;

    @TableField("local_blk_write_time")
    private Double localBlkWriteTime;

    @TableField("temp_blk_read_time")
    private Double tempBlkReadTime;

    @TableField("temp_blk_write_time")
    private Double tempBlkWriteTime;

    @TableField("wal_records")
    private Long walRecords;

    @TableField("wal_fpi")
    private Long walFpi;

    @TableField("wal_bytes")
    private Short walBytes;

    @TableField("wal_buffers_full")
    private Long walBuffersFull;

    @TableField("jit_functions")
    private Long jitFunctions;

    @TableField("jit_generation_time")
    private Double jitGenerationTime;

    @TableField("jit_inlining_count")
    private Long jitInliningCount;

    @TableField("jit_inlining_time")
    private Double jitInliningTime;

    @TableField("jit_optimization_count")
    private Long jitOptimizationCount;

    @TableField("jit_optimization_time")
    private Double jitOptimizationTime;

    @TableField("jit_emission_count")
    private Long jitEmissionCount;

    @TableField("jit_emission_time")
    private Double jitEmissionTime;

    @TableField("jit_deform_count")
    private Long jitDeformCount;

    @TableField("jit_deform_time")
    private Double jitDeformTime;

    @TableField("parallel_workers_to_launch")
    private Long parallelWorkersToLaunch;

    @TableField("parallel_workers_launched")
    private Long parallelWorkersLaunched;

    @TableField("stats_since")
    private LocalDateTime statsSince;

    @TableField("minmax_stats_since")
    private LocalDateTime minmaxStatsSince;
}
