package com.device.management.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://localhost:5432/device_mangent_db", "postgres", "2003")
                .globalConfig(builder -> {
                    builder.author("xiaos") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("C:\\Projects\\device-management-backend\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> builder
                        .parent("com.device.management")
                        .entity("entity")
                        .service("service")
                )
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .enableRestStyle(); // 启用 REST 风格
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
