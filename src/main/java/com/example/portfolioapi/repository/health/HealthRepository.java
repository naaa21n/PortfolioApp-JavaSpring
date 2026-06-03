package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// Health Entity
import com.example.portfolioapi.entity.health.Health;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Optional
import java.util.Optional;

// Date
import java.time.LocalDate;

// =========================
// Health Repository
// =========================
//
// Healthテーブル操作用Repository
//
// JpaRepositoryを継承することで
// 以下の機能が自動利用できる
//
// ・一覧取得
// ・1件取得
// ・登録
// ・更新
// ・削除
//
// 主な使用テーブル
//
// health
//
public interface HealthRepository
        extends JpaRepository<Health, String> {

    // =========================
    // 日付検索
    // =========================
    //
    // 指定日付の健康記録を取得
    //
    // 使用例
    //
    // healthRepository.findByDate(
    //     LocalDate.of(2025,6,20)
    // );
    //
    Optional<Health> findByDate(
            LocalDate date
    );

}