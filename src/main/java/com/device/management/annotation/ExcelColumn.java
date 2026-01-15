package com.device.management.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel列映射注解
 * 用于标记DTO字段对应的Excel列索引
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    /**
     * 列索引 (从0开始)
     */
    int index();
    
    /**
     * 列名 (可选，仅作描述)
     */
    String name() default "";
}
