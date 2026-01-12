package com.device.management.service;

import com.device.management.dto.LoginRequest;
import com.device.management.dto.LoginResponse;
import com.device.management.dto.UserDto;
import com.device.management.entity.User;
import com.device.management.exception.UnauthorizedException;
import com.device.management.repository.UserRepository;
import com.device.management.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 */
@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登录 | ユーザーのログイン
     */
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getUserId());
        if (user == null) {
            throw new UnauthorizedException("用户名或密码不正确");
        }

        // 验证密码 | パスワードを確認
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("用户名或密码不正确");
        }

        // 生成 JWT Token | JWTトークンを生成
        String token = jwtTokenProvider.generateToken(user.getUserId());

        // 构建响应 | 応答の構築
        UserDto userDTO = convertToDTO(user);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserInfo(userDTO);

        log.info("User {} logged in successfully", user.getUserId());
        return response;
    }


    /**
     * 转换为 DTO | DTOに変換
     */
    private UserDto convertToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setName(user.getUserId());
        dto.setDeptId(user.getDeptId());
        return dto;
    }
}
