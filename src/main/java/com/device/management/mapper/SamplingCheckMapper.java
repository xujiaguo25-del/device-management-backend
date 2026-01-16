package com.device.management.mapper;


import com.device.management.dto.SamplingCheckDTO;
import com.device.management.entity.SamplingCheck;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SamplingCheckMapper {
    @Mapping(target = "monitorName", ignore = true)
    SamplingCheckDTO convertToDto(SamplingCheck samplingCheck);
    List<SamplingCheckDTO> convertToDto(List<SamplingCheck> samplingChecks);
    default Page<SamplingCheckDTO> convertToDto(Page<SamplingCheck> page) {
        return page.map(this::convertToDto);
    }
    SamplingCheck convertToEntity(SamplingCheckDTO dto);

    //主キー、更新時間、作成時間はバックエンドで管理されます
    @Mapping(target = "samplingId", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    void updateEntityFromDto(SamplingCheckDTO dto, @MappingTarget SamplingCheck entity);
}

