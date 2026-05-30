package com.example.portfolioapi;

// =========================
// JUnit Import
// =========================

// テスト用アノテーション
import org.junit.jupiter.api.Test;

// Spring Bootテスト用
import org.springframework.boot.test.context.SpringBootTest;

// =========================
// Spring Boot Test Class
// =========================

// Spring Boot全体を起動して
// テストを行う
//
// 主な確認:
//
// ・Controller読込
// ・Repository読込
// ・Bean生成
// ・DI(依存性注入)
// ・ApplicationContext生成
@SpringBootTest
class PortfolioApiApplicationTests {

    // =========================
    // Context Load Test
    // =========================

    // テストメソッド
    @Test
    void contextLoads() {

        // 空でもOK
        //
        // Spring Bootが正常起動できれば成功
        //
        // 主な確認内容:
        //
        // ・設定エラーがないか
        // ・Bean生成失敗がないか
        // ・DI失敗がないか
        // ・ApplicationContext生成可能か
    }

}