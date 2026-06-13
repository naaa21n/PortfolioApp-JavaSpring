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
// Spring Import
// =========================

// Spring MVC
import org.springframework.web.bind.annotation.*;

// パスワードハッシュ化用
import org.springframework.security.crypto.password.PasswordEncoder;

// =========================
// Java Import
// =========================

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// =========================
// Auth Controller
// =========================
//
// ログイン・会員登録を担当するAPI Controller
//
// URL:
//
// POST /api/auth/register
// POST /api/auth/login
//
@RestController
@RequestMapping("/api/auth")

// Next.jsが別ポートで動く場合に必要になることがある
// 例:
// @CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    // =========================
    // Repository
    // =========================

    // Userテーブル操作用Repository
    private final UserRepository userRepository;

    // パスワードハッシュ化用
    private final PasswordEncoder passwordEncoder;

    // =========================
    // Constructor Injection
    // =========================
    //
    // Springが自動でRepositoryとPasswordEncoderを注入する
    //
    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // 会員登録API
    // =========================
    //
    // POST:
    // /api/auth/register
    //
    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestBody User user
    ) {

        // レスポンス用Map
        Map<String, Object> response =
                new HashMap<>();

        // =========================
        // 簡易入力チェック
        // =========================

        if (
                user.getEmail() == null ||
                        user.getEmail().isBlank() ||
                        user.getPassword() == null ||
                        user.getPassword().isBlank()
        ) {
            response.put("success", false);
            response.put("message", "メールアドレスとパスワードは必須です");

            return response;
        }

        // =========================
        // メール重複チェック
        // =========================

        // UserRepository に existsByEmail がある場合はこちらが簡単
        boolean exists =
                userRepository.existsByEmail(
                        user.getEmail()
                );

        if (exists) {
            response.put("success", false);
            response.put("message", "既に登録されています");

            return response;
        }

        // =========================
        // パスワードハッシュ化
        // =========================

        // 平文パスワードをBCrypt形式へ変換して保存する
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

        response.put("success", true);
        response.put("message", "登録成功");

        // 必要最低限のユーザー情報だけ返す
        // パスワードは返さない
        response.put("userId", savedUser.getId());
        response.put("name", savedUser.getName());
        response.put("email", savedUser.getEmail());

        return response;
    }

    // =========================
    // ログインAPI
    // =========================
    //
    // POST:
    // /api/auth/login
    //
    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody User user
    ) {

        // レスポンス用Map
        Map<String, Object> response =
                new HashMap<>();

        // =========================
        // 簡易入力チェック
        // =========================

        if (
                user.getEmail() == null ||
                        user.getEmail().isBlank() ||
                        user.getPassword() == null ||
                        user.getPassword().isBlank()
        ) {
            response.put("success", false);
            response.put("message", "メールアドレスとパスワードは必須です");

            return response;
        }

        // =========================
        // email検索
        // =========================

        Optional<User> optionalUser =
                userRepository.findByEmail(
                        user.getEmail()
                );

        // =========================
        // ユーザー存在チェック
        // =========================

        if (optionalUser.isEmpty()) {
            response.put("success", false);
            response.put("message", "メールまたはパスワードが違います");

            return response;
        }

        User existingUser =
                optionalUser.get();

        // =========================
        // パスワードチェック
        // =========================

        boolean passwordMatches =
                passwordEncoder.matches(
                        user.getPassword(),
                        existingUser.getPassword()
                );

        if (!passwordMatches) {
            response.put("success", false);
            response.put("message", "メールまたはパスワードが違います");

            return response;
        }

        // =========================
        // ログイン成功
        // =========================

        response.put("success", true);
        response.put("message", "ログイン成功");

        // 必要最低限のユーザー情報だけ返す
        // パスワードは返さない
        response.put("userId", existingUser.getId());
        response.put("name", existingUser.getName());
        response.put("email", existingUser.getEmail());

        return response;
    }
}