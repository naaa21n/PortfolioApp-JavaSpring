package com.example.portfolioapi.repository.health;

import com.example.portfolioapi.entity.health.DailyActivity;
import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// DailyActivity Repository
// =========================
//
// DailyActivityテーブル操作
//
// 主な機能
//
// ・一覧取得
// ・1件取得
// ・追加
// ・更新
// ・削除
//
public interface DailyActivityRepository
        extends JpaRepository<DailyActivity, String> {
}