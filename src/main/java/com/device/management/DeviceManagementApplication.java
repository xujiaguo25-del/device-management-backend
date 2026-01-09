package com.device.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 デバイス管理システム Spring Boot メインアプリケーション
 * 明示的なパッケージスキャンを追加 + Security自動設定を無効化し、
 * スキャン漏れと権限インターセプトの問題を解決します
 */
@SpringBootApplication(
        // スキャン範囲を明示的に指定し、コントローラー/サービス/設定パッケージのスキャンを強制
        scanBasePackages = {
                "com.device.management.controller",
                "com.device.management.service",
                "com.device.management.config",
                "com.device.management.security"
        },
        // 兜底的にSecurity自動設定を無効化し、権限インターセプトを完全に回避
        exclude = {
                SecurityAutoConfiguration.class,
                UserDetailsServiceAutoConfiguration.class
        }
)
// 明示的なコンポーネントスキャン（scanBasePackagesと効果は同一、スキャン漏れを回避）
@ComponentScan(basePackages = "com.device.management")
public class DeviceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagementApplication.class, args);
    }

}