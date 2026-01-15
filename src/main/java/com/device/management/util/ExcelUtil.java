package com.device.management.util;

import com.device.management.annotation.ExcelColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用Excel导入工具类
 */
@Slf4j
public class ExcelUtil {

    /**
     * 解析Excel文件为对象列表 (默认从第2行/索引1开始读取，跳过表头)
     *
     * @param file  上传的文件
     * @param clazz 目标类的Class对象
     * @param <T>   目标类型
     * @return 解析后的对象列表
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> clazz) {
        return importExcel(file, clazz, 1);
    }

    /**
     * 解析Excel文件为对象列表 (指定起始行)
     *
     * @param file          上传的文件
     * @param clazz         目标类的Class对象
     * @param startRowIndex 起始行索引 (0表示第一行)
     * @param <T>           目标类型
     * @return 解析后的对象列表
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> clazz, int startRowIndex) {
        if (file == null || file.isEmpty()) {
            return new ArrayList<>();
        }
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            // 默认读取第一个Sheet
            return parseSheet(workbook.getSheetAt(0), clazz, startRowIndex);
        } catch (Exception e) {
            log.error("Excel解析异常", e);
            throw new RuntimeException("Excel解析失败: " + e.getMessage());
        }
    }

    private static <T> List<T> parseSheet(Sheet sheet, Class<T> clazz, int startRowIndex) throws Exception {
        List<T> list = new ArrayList<>();
        // 获取类中所有带有 @ExcelColumn 注解的字段
        Field[] fields = clazz.getDeclaredFields();
        List<Field> mappedFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                field.setAccessible(true);
                mappedFields.add(field);
            }
        }

        // 遍历行
        for (Row row : sheet) {
            // 使用传入的 startRowIndex 控制起始行
            if (row.getRowNum() < startRowIndex) continue; 

            T instance = clazz.getDeclaredConstructor().newInstance();
            boolean hasData = false;

            for (Field field : mappedFields) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                int colIndex = annotation.index();
                Cell cell = row.getCell(colIndex);
                
                if (cell != null) {
                    Object value = getCellValue(cell, field.getType());
                    if (value != null) {
                        field.set(instance, value);
                        hasData = true;
                    }
                }
            }
            
            // 如果整行都没有有效数据，则忽略
            if (hasData) {
                list.add(instance);
            }
        }
        return list;
    }

    private static Object getCellValue(Cell cell, Class<?> fieldType) {
        if (cell == null) return null;

        // 根据单元格类型获取基础值
        Object value = null;
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue().trim();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                try {
                    value = cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    value = cell.getNumericCellValue();
                }
                break;
            default:
                break;
        }

        if (value == null) return null;

        // 类型转换：将Excel读取的值转换为字段需要的类型
        return convertType(value, fieldType);
    }

    private static Object convertType(Object value, Class<?> targetType) {
        String strVal = String.valueOf(value);
        
        if (targetType == String.class) {
            // 如果是数字转字符串，去掉可能的 .0
            if (value instanceof Double) {
                BigDecimal bd = new BigDecimal((Double) value);
                return bd.stripTrailingZeros().toPlainString();
            }
            return strVal;
        } else if (targetType == Integer.class || targetType == int.class) {
            if (value instanceof Number) return ((Number) value).intValue();
            try { return Integer.parseInt(strVal.replaceAll("[^0-9-]", "")); } catch (Exception e) { return null; }
        } else if (targetType == Long.class || targetType == long.class) {
            if (value instanceof Number) return ((Number) value).longValue();
            try { return Long.parseLong(strVal.replaceAll("[^0-9-]", "")); } catch (Exception e) { return null; }
        } else if (targetType == Double.class || targetType == double.class) {
            if (value instanceof Number) return ((Number) value).doubleValue();
            try { return Double.parseDouble(strVal); } catch (Exception e) { return null; }
        } else if (targetType == LocalDateTime.class) {
            if (value instanceof Date) {
                return ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        }
        return null;
    }
}