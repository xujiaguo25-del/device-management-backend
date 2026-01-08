package com.device.management.service;

import com.device.management.dto.*;

public interface AuthService {
    /**
     * ログイン機能
     * @param loginRequest
     * @return
     */
    ApiResponse<LoginDTO> login(LoginRequest loginRequest);

    /**
     * ユーザーログアウト
     * @return Result<Void>：ログアウト結果
     */
    ApiResponse<Void> logout();

    /**
     * パスワード変更機能
     * @param req
     * @return
     */
    ApiResponse<Void> changePassword(ChangePasswordRequest req);
}