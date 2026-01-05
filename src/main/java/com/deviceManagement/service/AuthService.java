package com.deviceManagement.service;

import com.deviceManagement.DTO.LoginRequest;
import com.deviceManagement.DTO.LoginResponse;
import com.deviceManagement.common.Result;

public interface AuthService {
    /**
     * 登录功能
     * @param loginRequest
     * @return
     */
    Result<LoginResponse> login(LoginRequest loginRequest);
}
