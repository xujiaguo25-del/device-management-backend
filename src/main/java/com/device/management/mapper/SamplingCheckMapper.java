package com.device.management.mapper;


import com.device.management.dto.SamplingCheckDTO;
import com.device.management.entity.SamplingCheck;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SamplingCheckMapper {
    SamplingCheckDTO convertToDto(SamplingCheck samplingCheck);
}

