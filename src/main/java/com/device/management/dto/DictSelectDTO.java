package com.device.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 字典选择项DTO（用于下拉框）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "字典选择项")
public class DictSelectDTO {

    @Schema(description = "值", example = "1")
    private Long value;

    @Schema(description = "显示标签", example = "Windows 11 Pro")
    private String label;

    @Schema(description = "字典类型代码", example = "OS")
    private String typeCode;

    @Schema(description = "字典类型名称", example = "操作系统")
    private String typeName;

    @Schema(description = "是否启用", example = "true")
    private Boolean enabled;
}