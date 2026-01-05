package com.jwt.service;

import com.jwt.DTO.LoginRequest;
import com.jwt.DTO.LoginResponse;
import com.jwt.DTO.UserInfo;
import com.jwt.common.Result;

public interface AuthService {
    /**
     * 登录功能
     * @param loginRequest
     * @return
     */
    Result<LoginResponse> login(LoginRequest loginRequest);
}
