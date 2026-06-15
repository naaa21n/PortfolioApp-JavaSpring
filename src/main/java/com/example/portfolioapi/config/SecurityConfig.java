package com.example.portfolioapi.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

@Configuration
public class SecurityConfig {

    // application.propertiesの
    // app.jwt.secretを取得
    @Value("${app.jwt.secret}")
    private String jwtSecretBase64;

    // JWTの発行元
    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    // =========================
    // PasswordEncoder設定
    // =========================

    @Bean
    public PasswordEncoder passwordEncoder() {

        // 現在使用しているBCryptをそのまま継続
        //
        // 登録時:
        // passwordEncoder.encode(rawPassword)
        //
        // ログイン時:
        // passwordEncoder.matches(
        //     rawPassword,
        //     hashedPassword
        // )
        return new BCryptPasswordEncoder();
    }

    // =========================
    // JWT署名用秘密鍵
    // =========================

    @Bean
    public SecretKey jwtSecretKey() {

        // Base64形式の環境変数を
        // バイトデータへ戻す
        byte[] decodedKey = Base64
                .getDecoder()
                .decode(jwtSecretBase64.trim());

        // HS256では最低256bit、
        // つまり32byte以上の鍵を使用する
        if (decodedKey.length < 32) {
            throw new IllegalStateException(
                    "JWT秘密鍵は32byte以上必要です"
            );
        }

        return new SecretKeySpec(
                decodedKey,
                "HmacSHA256"
        );
    }

    // =========================
    // JWT発行設定
    // =========================

    @Bean
    public JwtEncoder jwtEncoder(
            SecretKey jwtSecretKey
    ) {

        // JwtTokenServiceでJWTを作るときに使用
        return new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey)
        );
    }

    // =========================
    // JWT検証設定
    // =========================

    @Bean
    public JwtDecoder jwtDecoder(
            SecretKey jwtSecretKey
    ) {

        // Authorization: Bearer JWT
        // で受け取ったJWTを検証する
        NimbusJwtDecoder jwtDecoder =
                NimbusJwtDecoder
                        .withSecretKey(jwtSecretKey)
                        .macAlgorithm(MacAlgorithm.HS256)
                        .build();

        // issuerがportfolio-apiであるかも検証する
        jwtDecoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(
                        jwtIssuer
                )
        );

        return jwtDecoder;
    }

    // =========================
    // Security設定
    // =========================

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // Next.jsからAuthorizationヘッダーで
                // JWTを送るAPI構成なのでCSRFを無効化
                .csrf(csrf -> csrf.disable())

                // CORS設定を有効化
                // 詳細な許可URLは別途設定可能
                .cors(Customizer.withDefaults())

                // JWTはSpringのHTTP Sessionを使わない
                //
                // リクエストごとにJWTを確認する
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // URLごとのアクセス制御
                .authorizeHttpRequests(auth -> auth

                        // プリフライトリクエストを許可
                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        )
                        .permitAll()

                        // ユーザー登録は未ログインでも許可
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/auth/register"
                        )
                        .permitAll()

                        // ログインは未ログインでも許可
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/auth/login"
                        )
                        .permitAll()

                        // 上記以外のAPIはJWT必須
                        .anyRequest()
                        .authenticated()
                )

                // Basic認証は使用しない
                .httpBasic(
                        AbstractHttpConfigurer::disable
                )

                // Spring標準ログイン画面は使用しない
                .formLogin(
                        AbstractHttpConfigurer::disable
                )

                // Bearer JWT認証を有効化
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(
                                Customizer.withDefaults()
                        )
                );

        return http.build();
    }
}