package com.deviceManagement.controller;

import com.deviceManagement.dto.ChangePasswordRequest;
import com.deviceManagement.dto.ChangePasswordResponse;
import com.deviceManagement.dto.LoginRequest;
import com.deviceManagement.dto.LoginResponse;
import com.deviceManagement.common.Result;
import com.deviceManagement.dto.LogoutResponse;
import com.deviceManagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 認証Controller：ログイン、ユーザー情報インターフェースを処理する
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * ユーザーログインインターフェース
     * @param loginRequest ログインリクエストパラメータ（従業員番号+パスワード）
     * @return Result<String>：成功時はTokenを返し、失敗時はエラーメッセージを返す
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    /**
     * パスワード変更（ログイン後）
     * @param req
     * @param authHeader
     * @return
     */
    @PostMapping("/change-password")
    public Result<ChangePasswordResponse> changePassword(
            @Valid @RequestBody ChangePasswordRequest req,
            @RequestHeader("Authorization") String authHeader) {

        return authService.changePassword(req, authHeader);
    }

    /**
     * ユーザーログアウトインターフェース
     * @return Result<Void>：ログアウト結果
     */
    @PostMapping("/logout")
    public Result<Void> logout() {
        return authService.logout();
    }
}