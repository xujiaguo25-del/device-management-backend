package com.deviceManagement.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码枚举
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    // -------------------------- 成功状态码 --------------------------
    LOGIN_SUCCESS(200, "ログイン成功"),       // 匹配API登录成功响应
    LOGOUT_SUCCESS(200, "ログアウト成功"),     // 匹配API登出成功响应

    // -------------------------- 认证相关错误（401） --------------------------
    USER_NOT_FOUND(401, "ユーザーが存在しません"),  // 匹配API"用户不存在"错误
    PASSWORD_ERROR(401, "パスワードが正しくありません"), // 匹配API"密码错误"错误
    TOKEN_INVALID(401, "トークンが無効です"),       // 匹配API"令牌无效"错误
    TOKEN_EXPIRED(401, "トークンの有効期限が切れています"), // 匹配API"令牌过期"错误

    // -------------------------- 业务错误 --------------------------
    PARAM_ERROR(400, "userIdとpasswordは必須です"), // 匹配API"参数不足"错误
    FAIL(500, "システムエラー");                 // 匹配API"服务器内部错误"

    private final int code;       // HTTP状态码
    private final String message; // 响应消息
}
