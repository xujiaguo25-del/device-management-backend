package com.device.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ユーザーDTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private String userId;        // ユーザーID
    private String deptId;        // 部署番号
    private String userName;          // 氏名
    private DictDTO userType;      // ユーザータイプID（辞書項目：USER_TYPE 関連）

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // 作成日時
    private String creater;       // 作成者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 更新日時
    private String updater;       // 更新者

    // パスワード，通常はDTOに含めないが、登録時などに必要
    //private String password;

    private List<DeviceDTO> devices;
}