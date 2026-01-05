package com.deviceManagement.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {
    private static final String KEY = "1234567890abcdef"; // 16 位，放配置中心
    private static final String ALG  = "AES/ECB/PKCS5Padding";

    public static String encrypt(String raw) throws Exception {
        Cipher cipher = Cipher.getInstance(ALG);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        return Base64.getEncoder().encodeToString(cipher.doFinal(raw.getBytes()));
    }

    public static String decrypt(String base64) throws Exception {
        Cipher cipher = Cipher.getInstance(ALG);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
        return new String(cipher.doFinal(Base64.getDecoder().decode(base64)));
    }
}