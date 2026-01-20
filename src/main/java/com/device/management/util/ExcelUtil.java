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
 * 汎用Excelインポートユーティリティクラス
 * .xls および .xlsx 形式の自動検出に対応
 */
@Slf4j
public class ExcelUtil {

    /**
     * Excelファイルをオブジェクトリストに解析 (デフォルトで2行目/インデックス1から読み取り、ヘッダー行をスキップ)
     *
     * @param file  アップロードされたファイル
     * @param clazz 対象クラスのClassオブジェクト
     * @param <T>   対象タイプ
     * @return 解析後のオブジェクトリスト
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> clazz) {
        return importExcel(file, clazz, 1);
    }

    /**
     * Excelファイルをオブジェクトリストに解析 (開始行を指定)
     *
     * @param file          アップロードされたファイル
     * @param clazz         対象クラスのClassオブジェクト
     * @param startRowIndex 開始行インデックス (0は1行目を示す)
     * @param <T>           対象タイプ
     * @return 解析後のオブジェクトリスト
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> clazz, int startRowIndex) {
        if (file == null || file.isEmpty()) {
            log.warn("アップロードされたファイルは空です");
            return new ArrayList<>();
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.matches("^.+\\.(xls|xlsx|XLS|XLSX)$")) {
            log.error("サポートされていないファイル形式: {}", originalFilename);
            throw new RuntimeException(".xls および .xlsx 形式のExcelファイルのみサポートしています");
        }

        try (InputStream is = file.getInputStream()) {
            // ファイル形式を自動検出し、適切なパーサーを選択
            Workbook workbook = createWorkbook(is, originalFilename);

            // デフォルトで最初のSheetを読み取り
            return parseSheet(workbook.getSheetAt(0), clazz, startRowIndex);
        } catch (Exception e) {
            log.error("Excel解析例外", e);
            throw new RuntimeException("Excel解析に失敗しました: " + e.getMessage());
        }
    }

    /**
     * ファイル拡張子と内容に基づいて自動検出し、適切なWorkbookインスタンスを作成
     *
     * @param is               ファイル入力ストリーム
     * @param originalFilename 元のファイル名
     * @return Workbookインスタンス
     * @throws Exception ファイル形式が一致しないまたはその他のIO例外
     */
    private static Workbook createWorkbook(InputStream is, String originalFilename) throws Exception {
        // まずファイル拡張子に基づいて判断
        boolean isXLSX = originalFilename.toLowerCase().endsWith(".xlsx");
        boolean isXLS = originalFilename.toLowerCase().endsWith(".xls");

        if (isXLSX) {
            try {
                log.debug("拡張子に基づいて.xlsx形式と判断し、XSSFWorkbookで解析");
                return new XSSFWorkbook(is);
            } catch (Exception e) {
                log.warn("XSSFWorkbookでの解析に失敗し、形式を自動検出します", e);
                // 拡張子に基づく解析が失敗した場合、自動検出を試行
                return WorkbookFactory.create(is);
            }
        } else if (isXLS) {
            try {
                log.debug("拡張子に基づいて.xls形式と判断し、HSSFWorkbookで解析");
                return new HSSFWorkbook(is);
            } catch (Exception e) {
                log.warn("HSSFWorkbookでの解析に失敗し、形式を自動検出します", e);
                // 拡張子に基づく解析が失敗した場合、自動検出を試行
                return WorkbookFactory.create(is);
            }
        } else {
            // 未知の拡張子、POIの自動検出を使用
            log.debug("未知のファイル拡張子、WorkbookFactoryで自動検出");
            return WorkbookFactory.create(is);
        }
    }

    /**
     * WorkbookFactoryを使用してファイル形式を自動検出（最も安全な方法）
     * 注意：このメソッドはより多くのメモリを消費しますが、未知の形式のファイルに対して最も信頼性が高い
     */
    private static Workbook createWorkbookAutoDetect(InputStream is) throws Exception {
        try {
            // WorkbookFactoryはファイル形式を自動検出し、適切なWorkbookインスタンスを作成
            // .xls (HSSF) および .xlsx (XSSF) 形式に対応
            return WorkbookFactory.create(is);
        } catch (Exception e) {
            log.error("Excelファイル形式を識別できません", e);
            throw new RuntimeException("識別できないExcelファイル形式です。ファイルが有効な.xlsまたは.xlsx形式であることを確認してください");
        }
    }

    private static <T> List<T> parseSheet(Sheet sheet, Class<T> clazz, int startRowIndex) throws Exception {
        List<T> list = new ArrayList<>();
        // クラス内の @ExcelColumn アノテーションが付与されたすべてのフィールドを取得
        Field[] fields = clazz.getDeclaredFields();
        List<Field> mappedFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                field.setAccessible(true);
                mappedFields.add(field);
            }
        }

        // 総行数と実際に処理された行数を記録
        int totalRows = sheet.getPhysicalNumberOfRows();
        int processedRows = 0;
        int skippedRows = 0;

        log.debug("Excel解析を開始、総行数: {}、開始行インデックス: {}", totalRows, startRowIndex);

        // 行をループ処理
        for (Row row : sheet) {
            // 渡された startRowIndex を使用して開始行を制御
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

            // 行全体に有効なデータがない場合は無視
            if (hasData) {
                list.add(instance);
                processedRows++;
            } else {
                skippedRows++;
                log.debug("{}行目に有効なデータがないため、スキップしました", row.getRowNum() + 1);
            }
        }

        log.info("Excel解析完了：総行数={}、有効データ行数={}、スキップ行数={}",
                totalRows, processedRows, skippedRows);
        return list;
    }

    private static Object getCellValue(Cell cell, Class<?> fieldType) {
        if (cell == null) return null;

        // セルタイプを取得（新旧POIバージョンに対応）
        CellType cellType;
        try {
            // POI 3.17+ は列挙型方式を使用
            cellType = cell.getCellType();
        } catch (NoSuchMethodError e) {
            // POI 4.0+ は新しいAPIを使用
            cellType = cell.getCellType();
        }

        // セルタイプに基づいて基本値を取得
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
                log.warn("セルにエラー値が含まれています、行{}列{}",
                        cell.getRowIndex() + 1, cell.getColumnIndex() + 1);
                value = null;
                break;
            default:
                log.debug("未知のセルタイプ: {}", cellType);
                break;
        }

        if (value == null) return null;

        // 型変換：Excelから読み取った値をフィールドに必要な型に変換
        return convertType(value, fieldType);
    }

    private static Object convertType(Object value, Class<?> targetType) {
        String strVal = String.valueOf(value);

        if (targetType == String.class) {
            // 数値を文字列に変換する場合、不要な .0 を削除
            if (value instanceof Double) {
                Double doubleValue = (Double) value;
                // 整数かどうかを確認
                if (doubleValue == doubleValue.longValue()) {
                    return String.valueOf(doubleValue.longValue());
                } else {
                    // BigDecimalを使用して指数表記を回避
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
                log.debug("値 '{}' を Integer に変換できません", strVal);
                return null;
            }
        } else if (targetType == Long.class || targetType == long.class) {
            if (value instanceof Number) return ((Number) value).longValue();
            try {
                return Long.parseLong(strVal.replaceAll("[^0-9-]", ""));
            } catch (Exception e) {
                log.debug("値 '{}' を Long に変換できません", strVal);
                return null;
            }
        } else if (targetType == Double.class || targetType == double.class) {
            if (value instanceof Number) return ((Number) value).doubleValue();
            try {
                return Double.parseDouble(strVal);
            } catch (Exception e) {
                log.debug("値 '{}' を Double に変換できません", strVal);
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

        log.debug("サポートされていない型変換: {} -> {}", value.getClass().getSimpleName(), targetType.getSimpleName());
        return null;
    }

    /**
     * Workbookインスタンスの詳細情報を取得（デバッグ用）
     */
    public static String getWorkbookInfo(Workbook workbook) {
        StringBuilder info = new StringBuilder();

        if (workbook instanceof HSSFWorkbook) {
            info.append("Excel形式: .xls (HSSF - Excel 97-2003)");
        } else if (workbook instanceof XSSFWorkbook) {
            info.append("Excel形式: .xlsx (XSSF - Excel 2007+)");
        } else {
            info.append("Excel形式: 未知のタイプ (").append(workbook.getClass().getSimpleName()).append(")");
        }

        info.append("\nSheet数: ").append(workbook.getNumberOfSheets());

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            info.append("\nSheet[").append(i).append("]: ")
                    .append(sheet.getSheetName())
                    .append(" (行数: ").append(sheet.getPhysicalNumberOfRows()).append(")");
        }

        return info.toString();
    }
}