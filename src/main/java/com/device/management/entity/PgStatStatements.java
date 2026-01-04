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
@TableName("pg_stat_statements")
public class PgStatStatements {

    private Long userid;

    private Long dbid;

    private Boolean toplevel;

    private Long queryid;

    private String query;

    private Long plans;

    private Double totalPlanTime;

    private Double minPlanTime;

    private Double maxPlanTime;

    private Double meanPlanTime;

    private Double stddevPlanTime;

    private Long calls;

    private Double totalExecTime;

    private Double minExecTime;

    private Double maxExecTime;

    private Double meanExecTime;

    private Double stddevExecTime;

    private Long rows;

    private Long sharedBlksHit;

    private Long sharedBlksRead;

    private Long sharedBlksDirtied;

    private Long sharedBlksWritten;

    private Long localBlksHit;

    private Long localBlksRead;

    private Long localBlksDirtied;

    private Long localBlksWritten;

    private Long tempBlksRead;

    private Long tempBlksWritten;

    private Double sharedBlkReadTime;

    private Double sharedBlkWriteTime;

    private Double localBlkReadTime;

    private Double localBlkWriteTime;

    private Double tempBlkReadTime;

    private Double tempBlkWriteTime;

    private Long walRecords;

    private Long walFpi;

    private Short walBytes;

    private Long walBuffersFull;

    private Long jitFunctions;

    private Double jitGenerationTime;

    private Long jitInliningCount;

    private Double jitInliningTime;

    private Long jitOptimizationCount;

    private Double jitOptimizationTime;

    private Long jitEmissionCount;

    private Double jitEmissionTime;

    private Long jitDeformCount;

    private Double jitDeformTime;

    private Long parallelWorkersToLaunch;

    private Long parallelWorkersLaunched;

    private LocalDateTime statsSince;

    private LocalDateTime minmaxStatsSince;
}
