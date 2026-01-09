package com.device.management.controller;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.SamplingCheckDTO;
import com.device.management.service.SamplingCheckService;
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

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/security-checks")
public class SamplingCheckController {
    @Autowired
    private SamplingCheckService samplingCheckService;
    
    //データを更新
    @PutMapping("/{sampling_id}")
    public ApiResponse<SamplingCheckDTO> updateSamplingCheck(
            @PathVariable String sampling_id,             //URLパスからパラメータを取得する
            @Valid @RequestBody SamplingCheckDTO dto) {   //リクエストボディからJSONデータを取得し、DTOに自動でマッピングします。
        try {
            return ApiResponse.success("更新成功", samplingCheckService.update(sampling_id,dto));
        }
        catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    //データを削除
    @DeleteMapping("/{sampling_id}")
    public ApiResponse<Void> deleteSamplingCheck(@PathVariable String sampling_id) {

        try {
            samplingCheckService.delete(sampling_id);                                    //Service層を呼び出してデータを削除する
            return ApiResponse.success("删除成功", null);                      //成功レスポンスを返す
        } catch (RuntimeException e) {
            return ApiResponse.error(404, "删除失败: " + e.getMessage());     //データが存在しません
        }
    }
}
