package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// Entity
import com.example.portfolioapi.entity.health.DailyActivity;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// DailyActivity Repository
// =========================
//
// DailyActivityテーブル操作用Repository
//
// 主な機能
//
// ・一覧取得
// ・ID検索
// ・追加
// ・更新
// ・削除
//
// JpaRepositoryから継承される
// 標準CRUD機能を利用する
//
// 管理対象
//
// ・睡眠
// ・仕事
// ・勉強
// ・運動
// ・読書
// ・食事
//
// タイムライン形式の
// 行動履歴機能で利用する
//
public interface DailyActivityRepository
        extends JpaRepository<DailyActivity, String> {

}


//消す予定