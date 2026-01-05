package com.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secretKey; // 假设该密钥是URL安全Base64编码格式

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    /**
     * 生成SecretKey对象：处理URL安全Base64编码的密钥
     */
    private SecretKey getSigningKey() {
        try {
            // 使用URL安全Base64解码器解码密钥（解决'-'字符问题）
            byte[] keyBytes = java.util.Base64.getUrlDecoder().decode(secretKey);
            // 验证密钥长度（HS256需≥32字节）
            if (keyBytes.length < 32) {
                throw new IllegalStateException("密钥长度不足，HS256算法要求至少32字节（解码后）");
            }
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            log.error("密钥解码失败，可能不是URL安全Base64格式：{}", e.getMessage());
            throw new RuntimeException("密钥格式错误", e);
        }
    }

    /**
     * 生成Token：使用SecretKey签名，避免Base64解码异常
     */
    public String generateToken(String userId, Long userTypeId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("员工编号不能为空");
        }
        if (userTypeId == null) {
            throw new IllegalArgumentException("用户类型ID不能为空");
        }
        if (expirationTime <= 0) {
            throw new IllegalStateException("Token过期时间必须大于0");
        }

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(userId.trim())
                .claim("userType", userTypeId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 使用SecretKey签名
                .compact();
    }

    /**
     * 解析Token（私有方法）
     */
    private Claims parseToken(String token) throws JwtException {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token不能为空");
        }
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token.trim())
                .getBody();
    }

    /**
     * 获取员工编号
     */
    public String getUserIdFromToken(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * 获取用户类型ID
     */
    public Long getUserTypeIdFromToken(String token) {
        return parseToken(token).get("userType", Long.class);
    }

    /**
     * 验证Token（增强异常日志）
     */
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("Token已过期：{}", e.getMessage());
            return false;
        } catch (SignatureException e) {
            log.warn("Token签名无效：{}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.warn("Token格式错误：{}", e.getMessage());
            return false;
        } catch (JwtException e) {
            log.warn("Token验证失败：{}", e.getMessage());
            return false;
        }
    }
}