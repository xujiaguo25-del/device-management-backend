package com.device.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * 统一字典DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "字典项信息")
public class DictDTO {

    @Schema(description = "字典ID", example = "1")
    private Long dictId;

    @Schema(description = "字典类型代码", example = "OS")
    private String dictTypeCode;

    @Schema(description = "字典类型名称", example = "操作系统")
    private String dictTypeName;

    @Schema(description = "字典类型描述")
    private String dictTypeDescription;

    @Schema(description = "字典项名称", example = "Windows 11 Pro")
    private String dictItemName;

    @Schema(description = "排序号", example = "1")
    private Integer sort;

    @Schema(description = "是否启用", example = "true")
    private Boolean isEnabled;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建者")
    private String creater;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "更新者")
    private String updater;

    // ========== 扩展字段 ==========

    @Schema(description = "完整显示名称", example = "操作系统 - Windows 11 Pro")
    private String fullDisplayName;

    /**
     * 创建字典类型选择器需要的格式
     */
    public DictSelectDTO toSelectDTO() {
        return DictSelectDTO.builder()
                .value(dictId)
                .label(dictItemName)
                .typeCode(dictTypeCode)
                .typeName(dictTypeName)
                .enabled(isEnabled)
                .build();
    }
}