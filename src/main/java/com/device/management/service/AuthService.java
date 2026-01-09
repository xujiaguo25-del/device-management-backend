package com.device.management.service;

import com.device.management.dto.LoginRequest;
import com.device.management.dto.LoginResponse;
import com.device.management.dto.UserDTO;
import com.device.management.entity.User;
import com.device.management.exception.UnauthorizedException;
import com.device.management.repository.UserRepository;
import com.device.management.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 認証サービス
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
     * ユーザーログイン
     */
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new UnauthorizedException("用户名或密码不正确"));

        // パスワードを確認
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("用户名或密码不正确");
        }

        // JWT Token生成
        String token = jwtTokenProvider.generateToken(user.getUserId());

        // 应答
        UserDTO userDTO = convertToDTO(user);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserInfo(userDTO);

        log.info("User {} logged in successfully", user.getUserId());
        return response;
    }


    /**
     *  DTOに変換する
     */
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setDepartmentCode(user.getDepartmentCode());
        dto.setUserLevel(user.getUserLevel());
        dto.setCreatedDate(user.getCreatedDate());
        dto.setUpdatedDate(user.getUpdatedDate());
        return dto;
    }
}
