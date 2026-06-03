package com.example.portfolioapi.repository.health;

import com.example.portfolioapi.entity.health.Journal;

import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// Journal Repository
// =========================
//
// Journalテーブル操作用Repository
//
// Spring Data JPAによって
// 基本CRUD機能を自動提供する
//
// 主な機能
//
// ・一覧取得
// ・ID検索
// ・追加
// ・更新
// ・削除
//
// 利用Entity
//
// ・Journal
//
public interface JournalRepository
        extends JpaRepository<Journal, String> {

}