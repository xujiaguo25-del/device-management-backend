package com.deviceManagement.service;

import com.deviceManagement.dto.ChangePasswordRequest;
import com.deviceManagement.dto.ChangePasswordResponse;
import com.deviceManagement.dto.LoginRequest;
import com.deviceManagement.dto.LoginResponse;
import com.deviceManagement.dto.ApiResponse;

public interface AuthService {
    /**
     * ログイン機能
     * @param loginRequest
     * @return
     */
    ApiResponse<LoginResponse> login(LoginRequest loginRequest);

    /**
     * ユーザーログアウト
     * @return Result<Void>：ログアウト結果
     */
    ApiResponse<Void> logout();



    /**
     * パスワード変更機能
     * @param req
     * @param authHeader
     * @return
     */
    ApiResponse<ChangePasswordResponse> changePassword(ChangePasswordRequest req, String authHeader);
}