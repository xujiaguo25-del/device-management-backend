package com.deviceManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id")
    private String userId; // 工号

    @Column(name = "dept_id")
    private String deptId; // 部门

    @Column(name = "name")
    private String name; // 姓名

    @Column(name = "user_type_id")
    private Long userTypeId; // 用户类型ID（关联dict表）

    @Column(name = "password")
    private String password; // 加密后的密码

    @Column(name = "create_time")
    private LocalDateTime createTime; // 创建时间

    @Column(name = "creater")
    private String creater; // 创建者

    @Column(name = "update_time")
    private LocalDateTime updateTime; // 更新时间

    @Column(name = "updater")
    private String updater; // 更新者
}