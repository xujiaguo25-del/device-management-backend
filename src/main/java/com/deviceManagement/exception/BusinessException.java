package com.deviceManagement.exception;

import com.deviceManagement.common.ApiResponseCode;
import lombok.Getter;

/**
 * カスタム業務例外クラス：ResultCode列挙型に強くバインドし、ハードコードされたエラーコードを排除
 */
@Getter
public class BusinessException extends RuntimeException {
    // 直接ResultCode列挙型オブジェクトを保持、エラーコードとデフォルトメッセージを含む
    private final ApiResponseCode apiResponseCode;

    /**
     * 標準コンストラクター：ResultCodeのデフォルトメッセージを使用
     * @param apiResponseCode エラー列挙型（例：USER_NOT_FOUND、PASSWORD_ERROR）
     */
    public BusinessException(ApiResponseCode apiResponseCode) {
        super(apiResponseCode.getMessage());
        this.apiResponseCode = apiResponseCode;
    }

    /**
     * 拡張コンストラクター：カスタムメッセージを許可（ResultCodeのエラーコードを保持）
     * @param apiResponseCode エラー列挙型
     * @param customMessage カスタムエラーメッセージ（例：パラメータ検証の具体的なヒント）
     */
    public BusinessException(ApiResponseCode apiResponseCode, String customMessage) {
        super(customMessage);
        this.apiResponseCode = apiResponseCode;
    }

    /**
     * 旧コード互換性：getCode()メソッドを保持（ResultCodeから取得）
     * @return エラーコード（ResultCodeと一致）
     */
    public int getCode() {
        return apiResponseCode.getCode();
    }

    /**
     * getMessage()をオーバーライド：カスタムメッセージを優先、なければResultCodeのデフォルトメッセージを使用
     * @return 最終的なエラーメッセージ
     */
    @Override
    public String getMessage() {
        return super.getMessage() != null ? super.getMessage() : apiResponseCode.getMessage();
    }
}