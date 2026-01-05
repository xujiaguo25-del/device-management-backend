package com.deviceManagement.exception;

import com.deviceManagement.common.Result;
import com.deviceManagement.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindException;

/**
 * グローバル例外ハンドラー：全てのController層でスローされる例外を一括処理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * カスタム業務例外（BusinessException）を処理
     * @param e 業務例外オブジェクト
     * @return 標準化されたResultレスポンス
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        // 例外codeをResultCode列挙型にマッピング
        ResultCode resultCode = mapCodeToResultCode(e.getCode());
        logger.warn("業務エラー：code={}, message={}", resultCode.getCode(), resultCode.getMessage());
        // ジェネリック<Void>を指定してResult.errorメソッドの戻り値タイプに一致
        return Result.<Void>error(resultCode);
    }

    /**
     * パラメータ検証例外（@NotBlank、@Validなどによって発生する例外）を処理
     * @param e パラメータ検証例外オブジェクト
     * @return 標準化されたResultレスポンス
     */
    @ExceptionHandler(BindException.class)
    public Result<?> handleBindException(BindException e) {
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        logger.warn("パラメータ検証エラー：{}", errorMsg);
        // PARAM_ERROR列挙型のcodeを使用し、具体的なエラー情報を結合して返す
        return Result.<Void>error(ResultCode.PARAM_ERROR, errorMsg);
    }

    /**
     * その他の未キャッチなシステム例外を処理
     * @param e 例外オブジェクト
     * @return 標準化されたResultレスポンス
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleGlobalException(Exception e) {
        logger.error("システムエラー：", e); // 調査のための完全なスタックトレースを記録
        // FAIL列挙型を直接使用してシステムエラーを返す
        return Result.<Void>error(ResultCode.FAIL);
    }

    /**
     * 補助メソッド：業務例外codeをResultCode列挙型にマッピング
     * @param code 業務例外code
     * @return 一致するResultCode、デフォルトはシステムエラーを返す
     */
    private ResultCode mapCodeToResultCode(int code) {
        for (ResultCode codeEnum : ResultCode.values()) {
            if (codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        return ResultCode.FAIL;
    }
}