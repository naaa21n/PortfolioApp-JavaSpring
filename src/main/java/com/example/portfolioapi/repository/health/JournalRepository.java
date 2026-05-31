package com.example.portfolioapi.repository.health;

import com.example.portfolioapi.entity.health.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// Journal Repository
// =========================
//
// Journalテーブル操作
//
// 主な機能
//
// ・一覧取得
// ・1件取得
// ・追加
// ・更新
// ・削除
//
public interface JournalRepository
        extends JpaRepository<Journal, String> {
}