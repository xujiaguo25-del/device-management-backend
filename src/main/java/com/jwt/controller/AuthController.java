package com.jwt.controller;

import com.jwt.DTO.LoginRequest;
import com.jwt.DTO.LoginResponse;
import com.jwt.common.Result;
import com.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 认证授权Controller：处理登录、用户信息查询接口
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 用户登录接口
     * @param loginRequest 登录请求参数（员工编号+密码）
     * @return Result<String>：成功返回Token，失败返回错误信息
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}