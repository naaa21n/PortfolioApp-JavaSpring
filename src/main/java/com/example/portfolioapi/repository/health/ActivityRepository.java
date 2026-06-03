package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// Entity
import com.example.portfolioapi.entity.health.Activity;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Optional
import java.util.Optional;

// =========================
// Activity Repository
// =========================
//
// Activityテーブル操作用Repository
//
// 主な機能
//
// ・全件取得
// ・ID検索
// ・保存
// ・更新
// ・削除
//
// JpaRepositoryから継承される
// 標準CRUD機能を利用
//
// 独自機能
//
// ・日付でActivity取得
//
public interface ActivityRepository
        extends JpaRepository<Activity, String> {

    // =========================
    // 日付検索
    // =========================
    //
    // 指定された日付の
    // Activityデータを取得
    //
    // 使用例
    //
    // findByDate("2026-05-31")
    //
    Optional<Activity> findByDate(
            String date
    );
}