package com.device.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private String userId;
    private String deptId;
    private String name;
    private String userTypeName;
    private DictDTO userType;      // ユーザータイプID（辞書項目：USER_TYPE 関連）
}
