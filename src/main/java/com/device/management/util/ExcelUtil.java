package com.device.management.util;

import com.device.management.annotation.ExcelColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
 * 支持 .xls 和 .xlsx 格式自动检测
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
            log.warn("上传的文件为空");
            return new ArrayList<>();
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.matches("^.+\\.(xls|xlsx|XLS|XLSX)$")) {
            log.error("文件格式不支持: {}", originalFilename);
            throw new RuntimeException("只支持 .xls 和 .xlsx 格式的Excel文件");
        }

        try (InputStream is = file.getInputStream()) {
            // 自动检测文件格式并选择合适的解析器
            Workbook workbook = createWorkbook(is, originalFilename);

            // 默认读取第一个Sheet
            return parseSheet(workbook.getSheetAt(0), clazz, startRowIndex);
        } catch (Exception e) {
            log.error("Excel解析异常", e);
            throw new RuntimeException("Excel解析失败: " + e.getMessage());
        }
    }

    /**
     * 根据文件扩展名和内容自动检测并创建合适的Workbook实例
     *
     * @param is               文件输入流
     * @param originalFilename 原始文件名
     * @return Workbook实例
     * @throws Exception 文件格式不匹配或其他IO异常
     */
    private static Workbook createWorkbook(InputStream is, String originalFilename) throws Exception {
        // 首先根据文件扩展名判断
        boolean isXLSX = originalFilename.toLowerCase().endsWith(".xlsx");
        boolean isXLS = originalFilename.toLowerCase().endsWith(".xls");

        if (isXLSX) {
            try {
                log.debug("根据扩展名判断为.xlsx格式，使用XSSFWorkbook解析");
                return new XSSFWorkbook(is);
            } catch (Exception e) {
                log.warn("使用XSSFWorkbook解析失败，尝试自动检测格式", e);
                // 如果按扩展名解析失败，尝试自动检测
                return WorkbookFactory.create(is);
            }
        } else if (isXLS) {
            try {
                log.debug("根据扩展名判断为.xls格式，使用HSSFWorkbook解析");
                return new HSSFWorkbook(is);
            } catch (Exception e) {
                log.warn("使用HSSFWorkbook解析失败，尝试自动检测格式", e);
                // 如果按扩展名解析失败，尝试自动检测
                return WorkbookFactory.create(is);
            }
        } else {
            // 未知扩展名，使用POI的自动检测
            log.debug("未知文件扩展名，使用WorkbookFactory自动检测");
            return WorkbookFactory.create(is);
        }
    }

    /**
     * 使用WorkbookFactory自动检测文件格式（最安全的方式）
     * 注意：此方法会消耗更多内存，但对于未知格式文件最可靠
     */
    private static Workbook createWorkbookAutoDetect(InputStream is) throws Exception {
        try {
            // WorkbookFactory会自动检测文件格式并创建合适的Workbook实例
            // 它能够处理.xls (HSSF) 和 .xlsx (XSSF) 格式
            return WorkbookFactory.create(is);
        } catch (Exception e) {
            log.error("无法识别Excel文件格式", e);
            throw new RuntimeException("无法识别的Excel文件格式，请确保文件是有效的.xls或.xlsx格式");
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

        // 记录总行数和实际处理行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        int processedRows = 0;
        int skippedRows = 0;

        log.debug("开始解析Excel，总行数: {}，起始行索引: {}", totalRows, startRowIndex);

        // 遍历行
        for (Row row : sheet) {
            // 使用传入的 startRowIndex 控制起始行
            if (row.getRowNum() < startRowIndex) {
                skippedRows++;
                continue;
            }

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
                processedRows++;
            } else {
                skippedRows++;
                log.debug("第{}行没有有效数据，已跳过", row.getRowNum() + 1);
            }
        }

        log.info("Excel解析完成：总行数={}，有效数据行={}，跳过行={}",
                totalRows, processedRows, skippedRows);
        return list;
    }

    private static Object getCellValue(Cell cell, Class<?> fieldType) {
        if (cell == null) return null;

        // 获取单元格类型（兼容新旧POI版本）
        CellType cellType;
        try {
            // POI 3.17+ 使用枚举方式
            cellType = cell.getCellType();
        } catch (NoSuchMethodError e) {
            // POI 4.0+ 使用新的API
            cellType = cell.getCellType();
        }

        // 根据单元格类型获取基础值
        Object value = null;
        switch (cellType) {
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
            case BLANK:
                value = "";
                break;
            case ERROR:
                log.warn("单元格包含错误值，行{}列{}",
                        cell.getRowIndex() + 1, cell.getColumnIndex() + 1);
                value = null;
                break;
            default:
                log.debug("未知的单元格类型: {}", cellType);
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
                Double doubleValue = (Double) value;
                // 检查是否为整数
                if (doubleValue == doubleValue.longValue()) {
                    return String.valueOf(doubleValue.longValue());
                } else {
                    // 使用BigDecimal避免科学计数法
                    BigDecimal bd = new BigDecimal(doubleValue.toString());
                    return bd.stripTrailingZeros().toPlainString();
                }
            }
            return strVal;
        } else if (targetType == Integer.class || targetType == int.class) {
            if (value instanceof Number) return ((Number) value).intValue();
            try {
                return Integer.parseInt(strVal.replaceAll("[^0-9-]", ""));
            } catch (Exception e) {
                log.debug("无法将值 '{}' 转换为 Integer", strVal);
                return null;
            }
        } else if (targetType == Long.class || targetType == long.class) {
            if (value instanceof Number) return ((Number) value).longValue();
            try {
                return Long.parseLong(strVal.replaceAll("[^0-9-]", ""));
            } catch (Exception e) {
                log.debug("无法将值 '{}' 转换为 Long", strVal);
                return null;
            }
        } else if (targetType == Double.class || targetType == double.class) {
            if (value instanceof Number) return ((Number) value).doubleValue();
            try {
                return Double.parseDouble(strVal);
            } catch (Exception e) {
                log.debug("无法将值 '{}' 转换为 Double", strVal);
                return null;
            }
        } else if (targetType == LocalDateTime.class) {
            if (value instanceof Date) {
                return ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            if (value instanceof Boolean) return value;
            if (strVal.equalsIgnoreCase("true") || strVal.equals("1")) return true;
            if (strVal.equalsIgnoreCase("false") || strVal.equals("0")) return false;
        }

        log.debug("不支持的类型转换: {} -> {}", value.getClass().getSimpleName(), targetType.getSimpleName());
        return null;
    }

    /**
     * 获取Workbook实例的详细信息（用于调试）
     */
    public static String getWorkbookInfo(Workbook workbook) {
        StringBuilder info = new StringBuilder();

        if (workbook instanceof HSSFWorkbook) {
            info.append("Excel格式: .xls (HSSF - Excel 97-2003)");
        } else if (workbook instanceof XSSFWorkbook) {
            info.append("Excel格式: .xlsx (XSSF - Excel 2007+)");
        } else {
            info.append("Excel格式: 未知类型 (").append(workbook.getClass().getSimpleName()).append(")");
        }

        info.append("\nSheet数量: ").append(workbook.getNumberOfSheets());

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            info.append("\nSheet[").append(i).append("]: ")
                    .append(sheet.getSheetName())
                    .append(" (行数: ").append(sheet.getPhysicalNumberOfRows()).append(")");
        }

        return info.toString();
    }
}