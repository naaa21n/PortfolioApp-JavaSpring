package com.example.portfolioapi.entity.learning;

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
// LearningRecord Entity
// =========================
//
// 学習記録を管理するEntity
//
// 対応テーブル
// learning_records
//
// このクラスの1インスタンスが、
// learning_records テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・学習タイトル
// ・学習時間
// ・学習日
//
// 学習管理ページや
// 学習グラフ機能で利用する
//
@Entity
@Table(name = "learning_records")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このLearningRecordクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、learning_records テーブルは実質的に
//
// id
// user_id
// title
// study_minutes
// studied_on
// created_at
// updated_at
//
// のカラムを持つ
//
public class LearningRecord extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // learning_records テーブルの主キー
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
    // この学習記録が
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
    // Title
    // =========================
    //
    // 学習タイトル
    //
    // 例:
    // "Spring Boot"
    // "英語学習"
    // "React勉強"
    //
    // DBカラム名はJava変数名と同じ title なので
    // @Column(name = "title") は省略している
    //
    private String title;

    // =========================
    // Study Minutes
    // =========================
    //
    // 学習時間
    //
    // 単位:
    // 分
    //
    // 例:
    // 30
    // 60
    // 120
    //
    // Java側:
    // studyMinutes
    //
    // DB側:
    // study_minutes
    //
    @Column(name = "study_minutes")
    private Integer studyMinutes;

    // =========================
    // Studied On
    // =========================
    //
    // 学習日
    //
    // 例:
    // 2026-06-12
    //
    // Java側:
    // studiedOn
    //
    // DB側:
    // studied_on
    //
    @Column(name = "studied_on")
    private LocalDate studiedOn;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    // DBから取得したデータを
    // LearningRecordオブジェクトに変換するときなどに使われる
    //
    public LearningRecord() {
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
    // Title
    // =========================

    // 学習タイトル取得
    public String getTitle() {
        return title;
    }

    // 学習タイトル設定
    public void setTitle(String title) {
        this.title = title;
    }

    // =========================
    // Study Minutes
    // =========================

    // 学習時間取得
    public Integer getStudyMinutes() {
        return studyMinutes;
    }

    // 学習時間設定
    public void setStudyMinutes(Integer studyMinutes) {
        this.studyMinutes = studyMinutes;
    }

    // =========================
    // Studied On
    // =========================

    // 学習日取得
    public LocalDate getStudiedOn() {
        return studiedOn;
    }

    // 学習日設定
    public void setStudiedOn(LocalDate studiedOn) {
        this.studiedOn = studiedOn;
    }
}