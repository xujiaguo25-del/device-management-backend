package com.device.management.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * ユーザテーブル（ユーザ基本情報を保存）
 * </p>
 *
 * @author baomidou
 * @since 2026-01-04
 */
@Getter
@Setter
@ToString
public class Users {

    @TableId("user_id")
    private String userId;

    private String deptId;

    private String name;

    private Long userTypeId;

    private String password;

    private LocalDateTime createTime;

    private String creater;

    private LocalDateTime updateTime;

    private String updater;
}
