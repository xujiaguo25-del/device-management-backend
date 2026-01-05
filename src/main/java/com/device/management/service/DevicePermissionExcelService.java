package com.device.management.service;

import com.device.management.dto.DevicePermissionExcelVo;
import com.device.management.entity.Dict;
import com.device.management.repository.DictRepository;
import jakarta.annotation.PostConstruct;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DevicePermissionExcelService {
    private Map<Long, String> dictCache;
    private DictRepository dictRepository;

    @PostConstruct
    public void initDictCache() {
        List<Dict> dictList = dictRepository.findAll();
        dictCache = new HashMap<>();
        for (Dict dict : dictList) {
            Long key = dict.getId();
            dictCache.put(key, dict.getDictItemName());
        }
    }

    // 提供编码转名称的工具方法
    private String getDictName(String key) {
        return dictCache.get(key);
    }


    // 定义列宽（字符数）
    private static final int[] COLUMN_WIDTHS = {
            8,   // A: 编号
            30,  // B: 设备编号
            20,  // C: 电脑名
            25,  // D: IP地址
            10,  // E: 工号
            10,  // F: 姓名
            10,  // G: 级别
            15,  // H: 登录用户名
            10,  // I: 域名
            15,  // J: 域内组名
            15,  // K: 不加域理由
            15,  // L: SmartIT状态
            20,  // M: 不安装SmartIT理由
            12,  // N: USB状态
            20,  // O: USB开通理由
            15,  // P: 使用截止日期
            12,  // Q: 连接状态
            20,  // R: 无Symantec理由
            30   // S: 备注
    };

    /**
     * 导出设备使用权限清单
     */
    public void exportDevicePermissionList(List<DevicePermissionExcelVo> dataList,
                                           HttpServletResponse response) throws IOException {
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("设备使用权限清单");

        // 创建样式
        Map<String, CellStyle> styles = createStyles(workbook);

        // 设置列宽
        setColumnWidths(sheet);

        // 构建表格
        int currentRow = buildExcelTemplate(sheet, styles, dataList);

        // 设置响应头
        String fileName = "设备使用权限清单_" +
                new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "\"");

        // 写入输出流
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 创建各种样式
     */
    private Map<String, CellStyle> createStyles(Workbook workbook) {
        Map<String, CellStyle> styles = new java.util.HashMap<>();

        // 1. 标题样式（深蓝色背景，白色加粗）
//        CellStyle titleStyle = workbook.createCellStyle();
//        Font titleFont = workbook.createFont();
//        titleFont.setBold(true);
//        titleFont.setFontHeightInPoints((short) 14);
//        titleFont.setColor(IndexedColors.WHITE.getIndex());
//        titleStyle.setFont(titleFont);
//        titleStyle.setAlignment(HorizontalAlignment.CENTER);
//        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        titleStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
//        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        titleStyle.setBorderBottom(BorderStyle.THIN);
//        titleStyle.setBorderTop(BorderStyle.THIN);
//        titleStyle.setBorderLeft(BorderStyle.THIN);
//        titleStyle.setBorderRight(BorderStyle.THIN);
//        styles.put("title", titleStyle);

        // 2. 表头样式（灰色背景，加粗）
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setWrapText(true); // 自动换行
        styles.put("header", headerStyle);

        // 3. 数据样式（默认）
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setWrapText(true);
        styles.put("data", dataStyle);

        // 4. 居中数据样式
        CellStyle centerStyle = workbook.createCellStyle();
        centerStyle.cloneStyleFrom(dataStyle);
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        styles.put("center", centerStyle);

        // 5. 备注样式（斜体）
        CellStyle remarkStyle = workbook.createCellStyle();
        Font remarkFont = workbook.createFont();
        remarkFont.setItalic(true);
        remarkStyle.setFont(remarkFont);
        remarkStyle.cloneStyleFrom(dataStyle);
        styles.put("remark", remarkStyle);

        // 6. 日期样式
        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle.cloneStyleFrom(dataStyle);
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        styles.put("date", dateStyle);

        return styles;
    }

    /**
     * 设置列宽
     */
    private void setColumnWidths(Sheet sheet) {
        for (int i = 0; i < COLUMN_WIDTHS.length; i++) {
            sheet.setColumnWidth(i, COLUMN_WIDTHS[i] * 256);
        }
    }

    /**
     * 构建Excel模板
     */
    private int buildExcelTemplate(Sheet sheet, Map<String, CellStyle> styles,
                                   List<DevicePermissionExcelVo> dataList) {
        int rowNum = 0;

        // 第一行：编号信息
//        Row row0 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, row0, 0, 16, "编号", styles.get("title"));
//        createCell(row0, 17, "ISMSFM0502-1", styles.get("center"));

        // 第二行：版权和更新日期
//        Row row1 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, row1, 0, 16, "江苏海隆软件有限公司 版权所有 社外秘", styles.get("title"));
//        createCell(row1, 17, "更新日期", styles.get("header"));
//        createCell(row1, 18, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), styles.get("center"));

        // 第三行：主标题
//        Row row2 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, row2, 0, 18, "设备使用权限清单", styles.get("title"));

        // 第四行：部门
//        Row row3 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, row3, 0, 18, "部门", styles.get("title"));

        // 第五行：部门代码（这里应该根据数据动态显示）
//        Row row4 = sheet.createRow(rowNum++);
//        String deptCode = dataList.isEmpty() ? "" : dataList.get(0).getDepartmentCode();
//        createMergedCell(sheet, row4, 0, 18, deptCode, styles.get("title"));

        // 第六行：大表头（合并单元格）
        Row row5 = sheet.createRow(rowNum++);
        createCell(row5, 0, "编号", styles.get("header"));
        createMergedCell(sheet, row5, 1, 2, "设备", styles.get("header"));
        createMergedCell(sheet, row5, 3, 4, "使用者/责任人", styles.get("header"));
        createCell(row5, 5, "", styles.get("header"));
        createCell(row5, 6, "", styles.get("header"));
        createMergedCell(sheet, row5, 7, 10, "域", styles.get("header"));
        createMergedCell(sheet, row5, 11, 12, "SmartIT安装", styles.get("header"));
        createMergedCell(sheet, row5, 13, 15, "USB设备", styles.get("header"));
        createCell(row5, 16, "防病毒", styles.get("header"));
        createCell(row5, 17, "无Symantec理由", styles.get("header"));
        createCell(row5, 18, "备注", styles.get("header"));

        // 第七行：详细表头
        Row row6 = sheet.createRow(rowNum++);
        String[] headers = {
                "", "设备编号", "电脑名", "IP地址", "工号", "姓名", "级别", "登录用户名",
                "域名", "域内组名", "不加域理由", "SmartIT状态", "不安装SmartIT理由",
                "USB状态", "USB开通理由", "使用截止日期", "连接状态", "", ""
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = row6.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(styles.get("header"));
        }

        // 填充数据行
        rowNum = fillDataRows(sheet, styles, dataList, rowNum);

        return rowNum;
    }

    /**
     * 填充数据行
     */
    private int fillDataRows(Sheet sheet, Map<String, CellStyle> styles,
                             List<DevicePermissionExcelVo> dataList, int startRow) {
        int rowNum = startRow;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < dataList.size(); i++) {
            DevicePermissionExcelVo item = dataList.get(i);
            Row row = sheet.createRow(rowNum++);

            // 编号
            createCell(row, 0, item.getNumber() != null ? item.getNumber().toString() : "", styles.get("center"));

            // 设备信息
            createCell(row, 1, item.getDeviceNumber(), styles.get("data"));
            createCell(row, 2, item.getComputerName(), styles.get("data"));
            createCell(row, 3, item.getIpAddress(), styles.get("data"));

            // 使用者信息
            createCell(row, 4, item.getEmployeeId(), styles.get("center"));
            createCell(row, 5, item.getEmployeeName(), styles.get("center"));
            createCell(row, 6, item.getDepartmentCode(), styles.get("center"));
            createCell(row, 7, item.getLoginUsername(), styles.get("data"));

            // 域信息
            createCell(row, 8, getDictName(item.getDomain()), styles.get("center"));
            createCell(row, 9, item.getDomainGroup(), styles.get("data"));
            createCell(row, 10, item.getNoDomainReason(), styles.get("data"));

            // SmartIT信息
            createCell(row, 11,getDictName(item.getSmartitStatus()), styles.get("center"));
            createCell(row, 12, item.getNoSmartitReason(), styles.get("data"));

            // USB信息
            createCell(row, 13, getDictName(item.getUsbStatus()), styles.get("center"));
            createCell(row, 14, item.getUsbOpenReason(), styles.get("data"));
            createCell(row, 15, item.getUseDeadline() != null ?
                    dateFormat.format(item.getUseDeadline()) : "", styles.get("date"));

            // 其他信息
            createCell(row, 16, getDictName(item.getConnectionStatus()), styles.get("center"));
            createCell(row, 17, item.getNoSymantecReason(), styles.get("data"));
            createCell(row, 18, item.getRemark(), styles.get("remark"));

            // 如果有多个IP，可能需要添加额外的行（这里简化处理）
//            if (item.getIpAddress() != null && item.getIpAddress().contains("\n")) {
//                String[] ips = item.getIpAddress().split("\n");
//                for (int j = 1; j < ips.length; j++) {
//                    Row extraRow = sheet.createRow(rowNum++);
//                    createCell(extraRow, 3, ips[j].trim(), styles.get("data"));
//                }
//            }
        }

        return rowNum;
    }

//    /**
//     * 添加说明和签名行
//     */
//    private int addExplanationRows(Sheet sheet, Map<String, CellStyle> styles, int startRow) {
//        int rowNum = startRow;
//
//        // 空行
//        sheet.createRow(rowNum++);
//
//        // 说明文字
//        Row explanationRow = sheet.createRow(rowNum++);
//        String explanation = "※1.复查表单流程\n①各部门：确认实际数据与此表单的一致性\n②网络设备科：复查，确认\n\n※2.各部门负责此表单的日常维护";
//        createMergedCell(sheet, explanationRow, 0, 15, explanation, styles.get("data"));
//        createCell(explanationRow, 16, "信息安全员", styles.get("center"));
//        createCell(explanationRow, 17, "设备管理科", styles.get("center"));
//
//        // 签名行1
//        Row signRow1 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, signRow1, 0, 15, "", styles.get("data"));
//        createCell(signRow1, 16, "王邵赟\n2025/8/29", styles.get("center"));
//        createCell(signRow1, 17, "夏军\n2025/8/29", styles.get("center"));
//
//        // 签名行2
//        Row signRow2 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, signRow2, 0, 15, "", styles.get("data"));
//        createCell(signRow2, 16, "（盖章）", styles.get("center"));
//        createCell(signRow2, 17, "（盖章）", styles.get("center"));
//
//        // 签名行3
//        Row signRow3 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, signRow3, 0, 15, "", styles.get("data"));
//        createCell(signRow3, 16, "…", styles.get("center"));
//        createCell(signRow3, 17, "（盖章）", styles.get("center"));
//
//        // 签名行4
//        Row signRow4 = sheet.createRow(rowNum++);
//        createMergedCell(sheet, signRow4, 0, 15, "", styles.get("data"));
//        createCell(signRow4, 16, "（盖章）", styles.get("center"));
//        createCell(signRow4, 17, "（盖章）", styles.get("center"));
//
//        return rowNum;
//    }

    /**
     * 创建合并单元格
     */
    private void createMergedCell(Sheet sheet, Row row, int firstCol, int lastCol,
                                  String value, CellStyle style) {
        if (firstCol < lastCol) {
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(),
                    firstCol, lastCol));
        }
        Cell cell = row.createCell(firstCol);
        cell.setCellValue(value);
        if (style != null) {
            cell.setCellStyle(style);
        }
    }

    /**
     * 创建普通单元格
     */
    private void createCell(Row row, int col, String value, CellStyle style) {
        Cell cell = row.createCell(col);
        cell.setCellValue(value);
        if (style != null) {
            cell.setCellStyle(style);
        }
    }
}