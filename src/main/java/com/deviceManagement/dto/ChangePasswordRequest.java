package com.deviceManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * パスワード変更のリクエスト
 */
@Data
//public class ChangePasswordRequest {
//    @NotBlank(message = "userIdは必須です")
//    private String userId;
//
//    @NotBlank(message = "現在のパスワードは必須です")
//    private String currentPassword;
//
//    @NotBlank(message = "新しいパスワードは必須です")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "新しいパスワードは8文字以上で、英字・数字・特殊文字（@$!%*?&）をそれぞれ1文字以上含める必要があります。")
//    private String newPassword;
//}
public class ChangePasswordRequest {
    @NotBlank
    private String userId;          // 明文

    @NotBlank(message = "現在のパスワード（暗号化）は必須です")
    private String currentPassword; // 密文

    @NotBlank(message = "新しいパスワード（暗号化）は必須です")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "新パスワードは8文字以上で英字・数字・特殊文字を含む必要があります")
    private String newPassword;     // 密文（解密后才会走这个正则）
}