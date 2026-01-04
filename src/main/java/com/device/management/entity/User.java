package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseAudit {
    @Id
    private String userId;

    private String deptId;
    private String name;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private Dict userType;

}