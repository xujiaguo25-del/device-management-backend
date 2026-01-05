package com.deviceManagement.service;

import com.deviceManagement.dto.ChangePasswordRequest;
import com.deviceManagement.dto.ChangePasswordResponse;
import com.deviceManagement.dto.LoginRequest;
import com.deviceManagement.dto.LoginResponse;
import com.deviceManagement.common.Result;
import com.deviceManagement.dto.LogoutResponse;

public interface AuthService {
    /**
     * ログイン機能
     * @param loginRequest
     * @return
     */
    Result<LoginResponse> login(LoginRequest loginRequest);

    /**
     * ユーザーログアウト
     * @return Result<Void>：ログアウト結果
     */
    Result<Void> logout();



    /**
     * パスワード変更機能
     * @param req
     * @param authHeader
     * @return
     */
    Result<ChangePasswordResponse> changePassword(ChangePasswordRequest req, String authHeader);
}