package com.example.portfolioapi.config;

// Springの設定クラスであることを示す
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring Security の設定用
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// パスワードを BCrypt でハッシュ化するためのクラス
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Security のFilter設定
import org.springframework.security.web.SecurityFilterChain;

// このクラスが設定クラスであることをSpringへ伝える
@Configuration
public class SecurityConfig {

    // =========================
    // PasswordEncoder設定
    // =========================

    // パスワードをハッシュ化するためのBean
    // Spring全体で使い回される
    @Bean
    public PasswordEncoder passwordEncoder() {

        // BCrypt方式でハッシュ化
        // register時:
        // passwordEncoder.encode(password)
        //
        // login時:
        // passwordEncoder.matches(raw, hashed)
        return new BCryptPasswordEncoder();
    }

    // =========================
    // Security設定
    // =========================

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // CSRF保護を無効化
                //
                // 今回は Next.js と Spring Boot を
                // API通信で使っているためOFFにしている
                //
                // JWT認証構成ではよく disable する
                .csrf(csrf -> csrf.disable())

                // リクエストごとのアクセス制御
                .authorizeHttpRequests(auth -> auth

                        // 現在は全APIを許可
                        //
                        // 例:
                        // /api/auth/login
                        // /api/tasks
                        // など全部アクセス可能
                        //
                        // 後でJWT認証を導入したら:
                        //
                        // .requestMatchers("/api/auth/**").permitAll()
                        // .anyRequest().authenticated()
                        //
                        // に変更する
                        .anyRequest().permitAll()
                )

                // Basic認証設定
                //
                // 現状は特に使っていないが、
                // Spring Security初期設定として追加
                .httpBasic(Customizer.withDefaults());

        // Security設定をSpringへ返す
        return http.build();
    }
}

//git管理OK
//「アプリのセキュリティルールを決めるファイル」
//・ログイン必要？
//・どのAPIを公開する？
//・パスワードどう暗号化する？