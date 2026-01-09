package com.device.management.service;

import com.device.management.dto.SamplingCheckDTO;
import com.device.management.entity.SamplingCheck;
import com.device.management.mapper.SamplingCheckMapper;
import com.device.management.repository.SamplingCheckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SamplingCheckService {
    @Autowired
    private SamplingCheckRepository samplingCheckRepository;
    @Autowired
    private SamplingCheckMapper samplingCheckMapper;

    //IDで検索
    public SamplingCheckDTO findById(String id) {
        log.info("find sampling check by id {}", id);
        SamplingCheck samplingCheck = samplingCheckRepository.findById(id).orElse(null);
        return samplingCheckMapper.convertToDto(samplingCheck);
    //報告番号で検索
    public List<SamplingCheckDTO> findByReportId(String reportId) {
        log.info("find sampling check by reportId {}", reportId);
        List<SamplingCheck> samplingChecks = samplingCheckRepository.findByReportId(reportId);
        return samplingCheckMapper.convertToDto(samplingChecks);
    }
}

