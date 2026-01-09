package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.entity.SamplingCheck;
import com.device.management.mapper.SamplingCheckMapper;
import com.device.management.service.SamplingCheckService;
import com.device.management.dto.SamplingCheckDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/security-checks")
public class SamplingCheckController {
    @Autowired
    private SamplingCheckService samplingCheckService;


    //liujiale
    //安全点検リストを取得
    @GetMapping
    public ApiResponse<List<SamplingCheckDTO>> getSamplingChecks(
            @RequestParam int page,                               //ページ番号を取得
            @RequestParam(defaultValue = "10") int size,         //1ページあたりに表示する情報件数（デフォルトは10件）
            @RequestParam(required = false) String deviceId,     //デバイスIDでフィルタリング可能
            @RequestParam(required = false) String userId) {     //ユーザーIDでフィルタリング可能

        try {
            Page<SamplingCheckDTO> result = samplingCheckService.getSamplingChecks(page, size, deviceId, userId);    //データの取得を試行
            return ApiResponse.page(
                    result.getContent(),       // 現在のページのデータリスト
                    result.getTotalElements(), // 総件数
                    page,                      // 現在のページ番号
                    size                       // 1ページあたりの件数
            );
        }
        catch (Exception e) {
            return ApiResponse.error(500, "查询失败: " + e.getMessage());    //エラー情報を返却
        }
    }

    //データを新規追加
    @PostMapping
    public ApiResponse<SamplingCheckDTO> addSamplingCheck(@Valid @RequestBody SamplingCheckDTO dto) {
        try{
            return ApiResponse.success("添加成功", samplingCheckService.create(dto));
        }
        catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());      //エラー情報を返却
        }
    }

}
