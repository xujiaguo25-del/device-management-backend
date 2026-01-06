package com.device.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * user DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String userName;
    private String departmentCode;
    private String userLevel;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    /** 

    private String userId; //ユーザID
    private String deptId; //部署番号
    private String name; //氏名
    private Long userTypeId; //ユーザタイプID（辞書項目：USER_TYPE 関連）
    private LocalDateTime createTime; //作成日時
    private String creater; //作成者
    private LocalDateTime updateTime; //更新日時
    private String updater; //更新者
    */
}
