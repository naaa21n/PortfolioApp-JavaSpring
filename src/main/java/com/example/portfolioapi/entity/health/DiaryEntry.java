package com.example.portfolioapi.entity.health;

// =========================
// Common Entity Import
// =========================
//
// created_at / updated_at を継承するため
//
import com.example.portfolioapi.entity.common.BaseEntity;

// =========================
// JPA Import
// =========================
//
// Entity / Table / Id / Column / GeneratedValue など、
// DBテーブルとJavaクラスを紐付けるために使用
//
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

// =========================
// DiaryEntry Entity
// =========================
//
// 日記データを管理するEntity
//
// 対応テーブル
// diary_entries
//
// このクラスの1インスタンスが、
// diary_entries テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・日記日付
// ・日記本文
//
// 健康と日記ページの
// 日記機能で利用する
//
@Entity
@Table(name = "diary_entries")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このDiaryEntryクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、diary_entries テーブルは実質的に
//
// id
// user_id
// diary_date
// content
// created_at
// updated_at
//
// のカラムを持つ
//
public class DiaryEntry extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // diary_entries テーブルの主キー
    //
    // UUID形式で自動生成される
    //
    // 例:
    // 550e8400-e29b-41d4-a716-446655440000
    //
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // =========================
    // User ID
    // =========================
    //
    // この日記データが
    // どのユーザーに紐づくデータかを表す
    //
    // DBカラム名:
    // user_id
    //
    // usersテーブルの id を参照する想定
    //
    // Java側:
    // userId
    //
    // DB側:
    // user_id
    //
    @Column(name = "user_id")
    private UUID userId;

    // =========================
    // Diary Date
    // =========================
    //
    // 日記の日付
    //
    // 例:
    // 2026-05-31
    //
    // Java側:
    // diaryDate
    //
    // DB側:
    // diary_date
    //
    @Column(name = "diary_date")
    private LocalDate diaryDate;

    // =========================
    // Content
    // =========================
    //
    // 日記本文
    //
    // 長文保存用
    //
    // DB型:
    // text
    //
    @Column(columnDefinition = "TEXT")
    private String content;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    // DBから取得したデータを
    // DiaryEntryオブジェクトに変換するときなどに使われる
    //
    public DiaryEntry() {
    }

    // =========================
    // Getter / Setter
    // =========================
    //
    // privateフィールドは外部クラスから直接アクセスできないため、
    // Getter / Setter 経由で値を取得・設定する
    //

    // =========================
    // ID
    // =========================

    // ID取得
    public UUID getId() {
        return id;
    }

    // ID設定
    //
    // 通常、idはUUIDで自動生成されるため、
    // アプリ側で手動設定する場面は少ない
    //
    public void setId(UUID id) {
        this.id = id;
    }

    // =========================
    // User ID
    // =========================

    // ユーザーID取得
    public UUID getUserId() {
        return userId;
    }

    // ユーザーID設定
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    // =========================
    // Diary Date
    // =========================

    // 日記日付取得
    public LocalDate getDiaryDate() {
        return diaryDate;
    }

    // 日記日付設定
    public void setDiaryDate(LocalDate diaryDate) {
        this.diaryDate = diaryDate;
    }

    // =========================
    // Content
    // =========================

    // 日記本文取得
    public String getContent() {
        return content;
    }

    // 日記本文設定
    public void setContent(String content) {
        this.content = content;
    }
}