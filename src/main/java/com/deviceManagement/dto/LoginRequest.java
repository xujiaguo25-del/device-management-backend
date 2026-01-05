package com.deviceManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログインリクエスト
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "社員番号は必要です。")
    private String userId;

    @NotBlank(message = "パスワードは必要です")
    private String password;
}