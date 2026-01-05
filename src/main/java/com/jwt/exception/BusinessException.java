package com.jwt.exception;

import com.jwt.common.ResultCode;
import lombok.Getter;

/**
 * 自定义业务异常类：与ResultCode枚举强绑定，消除硬编码错误码
 */
@Getter
public class BusinessException extends RuntimeException {
    // 直接持有ResultCode枚举对象，包含错误码和默认消息
    private final ResultCode resultCode;

    /**
     * 标准构造：使用ResultCode的默认消息
     * @param resultCode 错误枚举（如USER_NOT_FOUND、PASSWORD_ERROR）
     */
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * 扩展构造：允许自定义消息（保留ResultCode的错误码）
     * @param resultCode 错误枚举
     * @param customMessage 自定义错误消息（如参数校验的具体提示）
     */
    public BusinessException(ResultCode resultCode, String customMessage) {
        super(customMessage);
        this.resultCode = resultCode;
    }

    /**
     * 兼容旧代码：保留getCode()方法（通过ResultCode获取）
     * @return 错误码（与ResultCode一致）
     */
    public int getCode() {
        return resultCode.getCode();
    }

    /**
     * 覆盖getMessage()：优先返回自定义消息，无则用ResultCode默认消息
     * @return 最终错误消息
     */
    @Override
    public String getMessage() {
        return super.getMessage() != null ? super.getMessage() : resultCode.getMessage();
    }
}