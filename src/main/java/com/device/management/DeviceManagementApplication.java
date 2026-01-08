package com.device.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 设备管理系统 Spring Boot 主应用程序
 * 新增显式包扫描+禁用Security自动配置，解决扫描漏扫和权限拦截问题
 */
@SpringBootApplication(
        // 核心1：显式指定扫描范围，强制扫描控制器/服务/配置包
        scanBasePackages = {
                "com.device.management.controller",   // 控制器包
                "com.device.management.service",     // 服务包
                "com.device.management.config",      // 配置包
                "com.device.management.security"     // 安全相关包
        },
        // 核心2：兜底禁用Security自动配置，彻底避免权限拦截
        exclude = {
                SecurityAutoConfiguration.class,
                UserDetailsServiceAutoConfiguration.class
        }
)
// 双重保障：显式组件扫描（和scanBasePackages效果一致，避免漏扫）
@ComponentScan(basePackages = "com.device.management")
public class DeviceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagementApplication.class, args);
    }

}