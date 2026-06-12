package com.example.portfolioapi.controller;

// Userエンティティ
import com.example.portfolioapi.entity.User;

// Userテーブル操作用Repository
import com.example.portfolioapi.repository.UserRepository;

// Spring MVC
import org.springframework.web.bind.annotation.*;

// パスワードハッシュ化用
import org.springframework.security.crypto.password.PasswordEncoder;

// Map用
import java.util.HashMap;
import java.util.Map;

// REST API Controller
@RestController

// このControllerの共通URL
// 例:
// /api/auth/register
// /api/auth/login
@RequestMapping("/api/auth")

// Next.jsからのアクセス許可
public class AuthController {

    // =========================
    // Repository
    // =========================

    // Userテーブル操作
    private final UserRepository userRepository;

    // パスワードハッシュ化
    private final PasswordEncoder passwordEncoder;

    // =========================
    // Constructor Injection
    // =========================

    // Springが自動で注入する
    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {

        // UserRepository受け取り
        this.userRepository = userRepository;

        // PasswordEncoder受け取り
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // 会員登録API
    // =========================

    // POST:
    // /api/auth/register
    @PostMapping("/register")

    // JSONをUserへ変換
    public Map<String, Object> register(
            @RequestBody User user
    ) {

        // レスポンス用Map
        Map<String, Object> response =
                new HashMap<>();

        // =========================
        // メール重複チェック
        // =========================

        // emailでユーザー検索
        User existingUser =
                userRepository.findByEmail(
                        user.getEmail()
                );

        // 既に存在する場合
        if (existingUser != null) {

            response.put("success", false);

            response.put(
                    "message",
                    "既に登録されています"
            );

            return response;
        }

        // =========================
        // パスワードハッシュ化
        // =========================

        // 平文パスワードを
        // BCrypt形式へ変換
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        // =========================
        // DB保存
        // =========================

        // userテーブルへ保存
        userRepository.save(user);

        // =========================
        // 成功レスポンス
        // =========================

        response.put("success", true);

        response.put(
                "message",
                "登録成功"
        );

        return response;
    }

    // =========================
    // ログインAPI
    // =========================

    // POST:
    // /api/auth/login
    @PostMapping("/login")

    public Map<String, Object> login(
            @RequestBody User user
    ) {

        // レスポンス用Map
        Map<String, Object> response =
                new HashMap<>();

        // =========================
        // email検索
        // =========================

        User existingUser =
                userRepository.findByEmail(
                        user.getEmail()
                );

        // =========================
        // ログインチェック
        // =========================

        // ユーザー不存在
        // または
        // パスワード不一致
        if (

                existingUser == null ||

                        !passwordEncoder.matches(
                                user.getPassword(),
                                existingUser.getPassword()
                        )
        ) {

            response.put("success", false);

            response.put(
                    "message",
                    "メールまたはパスワードが違います"
            );

            return response;
        }

        // =========================
        // ログイン成功
        // =========================

        response.put("success", true);

        response.put(
                "message",
                "ログイン成功"
        );

        return response;
    }
}


//「ログイン・会員登録を担当するAPIファイル」
//会員登録
//ログイン
//を処理する場所