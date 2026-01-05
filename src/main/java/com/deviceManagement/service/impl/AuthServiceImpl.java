package com.deviceManagement.service.impl;

import com.deviceManagement.dto.*;
import com.deviceManagement.repository.DictRepository;
import com.deviceManagement.repository.UserRepository;
import com.deviceManagement.common.Result;
import com.deviceManagement.common.ResultCode;
import com.deviceManagement.entity.Dict;
import com.deviceManagement.entity.User;
import com.deviceManagement.exception.BusinessException;
import com.deviceManagement.service.AuthService;
import com.deviceManagement.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final DictRepository dictRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 用户登录：返回包含token和userInfo的登录结果
     * @param loginRequest 登录请求参数
     * @return Result<LoginResponse>：成功返回token+用户信息，失败返回错误枚举
     */
    @Override
    public Result<LoginResponse> login(LoginRequest loginRequest) {
        // 1. 根据用户ID查询用户（不存在则抛出异常，使用ResultCode枚举直接构造）
        User user = userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));

        // 2. 验证密码（失败直接返回密码错误枚举）
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }

        // 3. 生成JWT Token
        String token = jwtUtil.generateToken(user.getUserId(), user.getUserTypeId());

        // 4. 构建UserInfo时查询用户类型（不存在则抛出系统错误）
        Dict userType = dictRepository.findByDictIdAndDictTypeCode(
                user.getUserTypeId(),
                "USER_TYPE"
        ).orElseThrow(() -> new BusinessException(ResultCode.FAIL));


        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setDeptId(user.getDeptId());
        userInfo.setName(user.getName());
        userInfo.setUserTypeName(userType.getDictItemName());

        // 5. 组装LoginResponse
        LoginResponse loginResponse = new LoginResponse(token, userInfo);

        // 6. 返回登录成功结果（使用Result的loginSuccess静态工厂方法）
        return Result.loginSuccess(loginResponse);
    }
    @Override
    @Transactional
    public Result<ChangePasswordResponse> changePassword(ChangePasswordRequest req,
                                                         String authHeader) {
        // 1. 解析并验证 JWT
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        if (!jwtUtil.validateToken(token)) {
            return Result.error(ResultCode.TOKEN_INVALID);
        }
        String tokenUserId = jwtUtil.getUserIdFromToken(token);

        // 2. 只能改自己的密码
        if (!tokenUserId.equals(req.getUserId())) {
            return Result.error(ResultCode.PARAM_ERROR, "只能修改当前登录账号的密码");
        }

        // 3. 校验旧密码
        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(req.getCurrentPassword(), user.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR, "当前密码输入错误");
        }

        // 4. 新旧密码不能相同
        if (passwordEncoder.matches(req.getNewPassword(), user.getPassword())) {
            return Result.error(ResultCode.FAIL, "新密码不能与旧密码相同");
        }

        // 5. 更新密码
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);

        // 6. 组装返回
        ChangePasswordResponse resp = new ChangePasswordResponse();
        resp.setCode(0);
        resp.setMsg("パスワードが更新されました。再度ログインしてください。");
        return Result.success(resp);
    }
}