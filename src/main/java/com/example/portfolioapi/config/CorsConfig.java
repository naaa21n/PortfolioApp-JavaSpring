package com.example.portfolioapi.config;

// application.properties / application-local.properties の値を読み込むため
import org.springframework.beans.factory.annotation.Value;

// このクラスをSpringの設定クラスとして扱うため
import org.springframework.context.annotation.Configuration;

// CORS設定を登録するため
import org.springframework.web.servlet.config.annotation.CorsRegistry;

// Spring MVCの設定をカスタマイズするため
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// =========================
// CORS設定クラス
// =========================
//
// Next.jsなど、別オリジンからSpring Boot APIへ
// アクセスできるようにするための設定クラス
//
// 例:
// Next.js : http://localhost:3000
// Spring  : http://localhost:8080
//
// ポート番号が違うため、CORS設定が必要になる
//
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // =========================
    // 許可するフロントエンドURL
    // =========================
    //
    // application-local.properties などに記載した値を読み込む
    //
    // 例:
    // app.cors.allowed-origin=http://localhost:3000
    //
    // 上記の値が allowedOrigin に入る
    //
    @Value("${app.cors.allowed-origin}")
    private String allowedOrigin;

    // =========================
    // CORS設定
    // =========================
    //
    // Spring Boot起動時に自動で呼び出される
    //
    // ここで
    // ・どのURLパターンに対して
    // ・どのフロントエンドURLからのアクセスを許可するか
    // ・どのHTTPメソッドを許可するか
    // を設定する
    //
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                // =====================
                // CORSを適用するAPIパス
                // =====================
                //
                // /api/** は
                // /api/healths
                // /api/learnings
                // /api/books
                // など、/api/ から始まる全APIを対象にする
                //
                .addMapping("/api/**")

                // =====================
                // 許可するアクセス元
                // =====================
                //
                // allowedOrigin には
                // application-local.properties の
                // app.cors.allowed-origin の値が入る
                //
                // 例:
                // http://localhost:3000
                //
                .allowedOrigins(allowedOrigin)

                // =====================
                // 許可するHTTPメソッド
                // =====================
                //
                // GET     : データ取得
                // POST    : 新規登録
                // PUT     : 更新
                // DELETE  : 削除
                // OPTIONS : CORSの事前確認リクエスト
                //
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )

                // =====================
                // 許可するリクエストヘッダー
                // =====================
                //
                // "*" は全てのヘッダーを許可するという意味
                //
                // 例:
                // Content-Type
                // Authorization
                // など
                //
                .allowedHeaders("*");
    }
}