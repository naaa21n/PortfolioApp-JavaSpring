package com.example.portfolioapi;

// =========================
// Spring Boot Import
// =========================

// Spring Boot起動クラス
import org.springframework.boot.SpringApplication;

// Spring Boot自動設定用
import org.springframework.boot.autoconfigure.SpringBootApplication;

// =========================
// Main Application Class
// =========================

// Spring Bootアプリとして認識
//
// 自動で:
//
// ・Controller読込
// ・Repository読込
// ・Entity読込
// ・Bean管理
// ・DI(依存性注入)
// ・Tomcat起動
// ・Spring Security設定
// ・JPA設定
//
// などを行う
@SpringBootApplication
public class PortfolioApiApplication {

    // =========================
    // Main Method
    // =========================

    // Javaアプリの開始地点
    //
    // 最初に実行される
    public static void main(String[] args) {

        // =========================
        // Spring Boot起動
        // =========================

        // PortfolioApiApplicationを起点に
        // Spring Boot全体を起動
        //
        // 実行される主な内容:
        //
        // ・組み込みTomcat起動
        // ・Controller読込
        // ・Repository読込
        // ・Entity読込
        // ・DB接続
        // ・Spring Security初期化
        // ・API待受開始
        SpringApplication.run(
                PortfolioApiApplication.class,
                args
        );
    }

}