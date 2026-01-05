package com.deviceManagement.common;

import com.deviceManagement.dto.ChangePasswordResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.deviceManagement.dto.LoginResponse;
import lombok.Data;


/**
 * 统一API响应结果类（与ResultCode枚举绑定，完全匹配API规范）
 * @param <T> 响应数据类型（登录成功时为LoginData，登出/错误时为Void）
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * ログイン成功レスポンス（APIのLoginResponseに一致）
     * @param loginData ログイン成功データ（token+userInfoを含む）
     * @return Result<LoginData>
     */
    public static Result<LoginResponse> loginSuccess(LoginResponse loginData) {
        return new Result<>(
                ResultCode.LOGIN_SUCCESS.getCode(),
                ResultCode.LOGIN_SUCCESS.getMessage(),
                loginData
        );
    }

    /**
     * ログアウト成功レスポンス（APIのLogoutResponseに一致）
     * @return Result<Void>
     */
    public static Result<Void> logoutSuccess() {
        return new Result<>(
                ResultCode.LOGOUT_SUCCESS.getCode(),
                ResultCode.LOGOUT_SUCCESS.getMessage(),
                null
        );
    }

    /**
     * エラーレスポンス（ジェネリック対応、任意の戻り値タイプに適合）
     * @param resultCode エラー列挙型
     * @return Result<T>：指定されたジェネリックのエラー結果
     */
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(
                resultCode.getCode(),
                resultCode.getMessage(),
                null
        );
    }
    //オーバーロードメソッド
    public static <T> Result<T> error(ResultCode resultCode, String customMessage) {
        return new Result<>(resultCode.getCode(), customMessage, null);
    }

    /**
     * パスワード変更成功レスポンス
     * @param data
     * @return
     */
    public static Result<ChangePasswordResponse> passwordChangedSuccess(ChangePasswordResponse data) {
        return new Result<>(
                ResultCode.PASSWORD_CHANGED_SUCCESS.getCode(),
                ResultCode.PASSWORD_CHANGED_SUCCESS.getMessage(),
                data
        );
    }
}