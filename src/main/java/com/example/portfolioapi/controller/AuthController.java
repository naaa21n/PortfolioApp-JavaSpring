package com.example.portfolioapi.controller;

// =========================
// Entity Import
// =========================

// Userエンティティ
import com.example.portfolioapi.entity.user.User;

// =========================
// Repository Import
// =========================

// Userテーブル操作用Repository
import com.example.portfolioapi.repository.user.UserRepository;

// =========================
// JWT Import
// =========================

// JWTを発行するService
import com.example.portfolioapi.security.JwtTokenService;

// =========================
// Spring Import
// =========================

// Spring MVC
import org.springframework.web.bind.annotation.*;

// パスワードのハッシュ化・照合用
import org.springframework.security.crypto.password.PasswordEncoder;

// 認証済みJWTをControllerで受け取る
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

// =========================
// Java Import
// =========================

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

// =========================
// Auth Controller
// =========================
//
// ログイン・会員登録を担当するAPI Controller
//
// POST /api/auth/register
// POST /api/auth/login
// GET  /api/auth/me
//
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // =========================
    // Repository・Service
    // =========================

    // Userテーブル操作用Repository
    private final UserRepository userRepository;

    // パスワードハッシュ化・照合用
    private final PasswordEncoder passwordEncoder;

    // JWT発行用Service
    private final JwtTokenService jwtTokenService;

    // =========================
    // Constructor Injection
    // =========================

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenService jwtTokenService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    // =========================
    // 会員登録API
    // =========================
    //
    // POST /api/auth/register
    //
    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestBody User user
    ) {

        Map<String, Object> response =
                new HashMap<>();

        // =========================
        // 入力チェック
        // =========================

        if (
                user.getEmail() == null ||
                        user.getEmail().isBlank() ||
                        user.getPassword() == null ||
                        user.getPassword().isBlank()
        ) {
            response.put(
                    "success",
                    false
            );

            response.put(
                    "message",
                    "メールアドレスとパスワードは必須です"
            );

            return response;
        }

        // メールアドレスの前後空白を削除し、
        // 小文字へ統一する
        String normalizedEmail =
                user.getEmail()
                        .trim()
                        .toLowerCase();

        user.setEmail(normalizedEmail);

        // =========================
        // メール重複チェック
        // =========================

        boolean exists =
                userRepository.existsByEmail(
                        normalizedEmail
                );

        if (exists) {
            response.put(
                    "success",
                    false
            );

            response.put(
                    "message",
                    "既に登録されています"
            );

            return response;
        }

        // =========================
        // パスワードハッシュ化
        // =========================

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        // =========================
        // DB保存
        // =========================

        User savedUser =
                userRepository.save(user);

        // =========================
        // 成功レスポンス
        // =========================

        response.put(
                "success",
                true
        );

        response.put(
                "message",
                "登録成功"
        );

        response.put(
                "userId",
                savedUser.getId()
        );

        response.put(
                "name",
                savedUser.getName()
        );

        response.put(
                "email",
                savedUser.getEmail()
        );

        return response;
    }

    // =========================
    // ログインAPI
    // =========================
    //
    // POST /api/auth/login
    //
    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody User user
    ) {

        Map<String, Object> response =
                new HashMap<>();

        // =========================
        // 入力チェック
        // =========================

        if (
                user.getEmail() == null ||
                        user.getEmail().isBlank() ||
                        user.getPassword() == null ||
                        user.getPassword().isBlank()
        ) {
            response.put(
                    "success",
                    false
            );

            response.put(
                    "message",
                    "メールアドレスとパスワードは必須です"
            );

            return response;
        }

        // メールアドレス表記を統一
        String normalizedEmail =
                user.getEmail()
                        .trim()
                        .toLowerCase();

        // =========================
        // email検索
        // =========================

        Optional<User> optionalUser =
                userRepository.findByEmail(
                        normalizedEmail
                );

        // =========================
        // ユーザー存在チェック
        // =========================

        if (optionalUser.isEmpty()) {
            response.put(
                    "success",
                    false
            );

            response.put(
                    "message",
                    "メールまたはパスワードが違います"
            );

            return response;
        }

        User existingUser =
                optionalUser.get();

        // =========================
        // パスワードチェック
        // =========================

        boolean passwordMatches =
                passwordEncoder.matches(

                        // ブラウザから送られた平文パスワード
                        user.getPassword(),

                        // DBに保存されているBCryptハッシュ
                        existingUser.getPassword()
                );

        if (!passwordMatches) {
            response.put(
                    "success",
                    false
            );

            response.put(
                    "message",
                    "メールまたはパスワードが違います"
            );

            return response;
        }

        // =========================
        // JWT発行
        // =========================

        String accessToken =
                jwtTokenService.createAccessToken(

                        // JWTのsubへ保存するユーザーID
                        existingUser.getId(),

                        // JWTのemailへ保存
                        existingUser.getEmail(),

                        // JWTのnameへ保存
                        existingUser.getName()
                );

        // =========================
        // ログイン成功
        // =========================

        response.put(
                "success",
                true
        );

        response.put(
                "message",
                "ログイン成功"
        );

        response.put(
                "userId",
                existingUser.getId()
        );

        response.put(
                "name",
                existingUser.getName()
        );

        response.put(
                "email",
                existingUser.getEmail()
        );

        // =========================
        // JWT情報
        // =========================

        // JWT本体
        response.put(
                "accessToken",
                accessToken
        );

        // Authorizationヘッダーで使用する種類
        response.put(
                "tokenType",
                "Bearer"
        );

        // JWTの有効時間・秒
        response.put(
                "expiresIn",
                jwtTokenService
                        .getExpiresInSeconds()
        );

        return response;
    }

    // =========================
    // ログインユーザー確認API
    // =========================
    //
    // GET /api/auth/me
    //
    // Authorization:
    // Bearer JWT
    //
    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        User user = userRepository.findById(userId)
                .orElseThrow();

        // ユーザID、ユーザ名、メールを格納し返却
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());

        return response;

        /*
        response.put(
                "success",
                true
        );

        // JWTのsubに保存したユーザーID
        response.put(
                "userId",
                jwt.getSubject()
        );

        // JWT内のemail
        response.put(
                "email",
                jwt.getClaimAsString(
                        "email"
                )
        );

        // JWT内のname
        response.put(
                "name",
                jwt.getClaimAsString(
                        "name"
                )
        );

        return response;
        */
    }
}