package com.device.management.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excelカラムマッピングアノテーション
 * DTOフィールドに対応するExcelカラムインデックスをマークするために使用する
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    /**
     * カラムインデックス (0から開始)
     */
    int index();

    /**
     * カラム名 (任意項目、説明用のみ)
     */
    String name() default "";
}
