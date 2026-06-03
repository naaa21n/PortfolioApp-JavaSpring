package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// Entity
import com.example.portfolioapi.entity.health.Diary;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// List
import java.util.List;

// =========================
// Diary Repository
// =========================
//
// Diaryテーブル操作用Repository
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
// 標準CRUD機能を利用する
//
// 独自機能
//
// ・日付で日記検索
//
public interface DiaryRepository
        extends JpaRepository<Diary, String> {

    // =========================
    // 日付検索
    // =========================
    //
    // 指定された日付の日記を取得
    //
    // 使用例
    //
    // findByDate("2026-05-31")
    //
    List<Diary> findByDate(
            String date
    );
}