package com.example.portfolioapi.dto.auth;

import java.util.UUID;

public record LoginResponse(

        // JWT本体
        String accessToken,

        // Bearer固定
        String tokenType,

        // 有効時間・秒
        long expiresIn,

        // ログインユーザー情報
        UUID userId,

        String email
) {
}