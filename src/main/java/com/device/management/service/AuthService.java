package com.device.management.service;

import com.device.management.dto.LoginRequest;
import com.device.management.dto.LoginDTO;
import com.device.management.dto.ApiResponse;

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

}