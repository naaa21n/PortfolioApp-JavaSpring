package com.example.portfolioapi.entity.health;

// =========================
// Common Entity Import
// =========================
//
// created_at / updated_at など、
// 全Entityで共通利用する項目を継承するため
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
// HealthRecord Entity
// =========================
//
// 健康記録を管理するEntity
//
// 対応テーブル
// health_records
//
// このクラスの1インスタンスが、
// health_records テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・記録日
// ・歩数
// ・運動時間
// ・睡眠時間
// ・水分摂取量
//
// 健康ダッシュボードや
// カレンダー機能で利用する
//
@Entity
@Table(name = "health_records")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このHealthRecordクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、health_records テーブルは実質的に
//
// id
// user_id
// record_date
// steps
// exercise_minutes
// sleep_hours
// water_ml
// created_at
// updated_at
//
// のカラムを持つ
//
public class HealthRecord extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // health_records テーブルの主キー
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
    // この健康記録が
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
    // Record Date
    // =========================
    //
    // 健康記録の日付
    //
    // 例:
    // 2026-05-31
    //
    // Java側:
    // recordDate
    //
    // DB側:
    // record_date
    //
    @Column(name = "record_date")
    private LocalDate recordDate;

    // =========================
    // Steps
    // =========================
    //
    // 歩数
    //
    // 単位:
    // 歩
    //
    // 例:
    // 5000
    // 10000
    //
    // DBカラム名はJava変数名と同じ steps なので
    // @Column(name = "steps") は省略している
    //
    private Integer steps;

    // =========================
    // Exercise Minutes
    // =========================
    //
    // 運動時間
    //
    // 単位:
    // 分
    //
    // 例:
    // 30
    // 60
    //
    // Java側:
    // exerciseMinutes
    //
    // DB側:
    // exercise_minutes
    //
    @Column(name = "exercise_minutes")
    private Integer exerciseMinutes;

    // =========================
    // Sleep Hours
    // =========================
    //
    // 睡眠時間
    //
    // 単位:
    // 時間
    //
    // 例:
    // 6.5
    // 7.0
    // 8.0
    //
    // Java側:
    // sleepHours
    //
    // DB側:
    // sleep_hours
    //
    @Column(name = "sleep_hours")
    private Double sleepHours;

    // =========================
    // Water Intake
    // =========================
    //
    // 水分摂取量
    //
    // 単位:
    // ml
    //
    // 例:
    // 1500
    // 2000
    //
    // Java側:
    // waterMl
    //
    // DB側:
    // water_ml
    //
    @Column(name = "water_ml")
    private Integer waterMl;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    // DBから取得したデータを
    // HealthRecordオブジェクトに変換するときなどに使われる
    //
    public HealthRecord() {
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
    // Record Date
    // =========================

    // 記録日取得
    public LocalDate getRecordDate() {
        return recordDate;
    }

    // 記録日設定
    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    // =========================
    // Steps
    // =========================

    // 歩数取得
    public Integer getSteps() {
        return steps;
    }

    // 歩数設定
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    // =========================
    // Exercise Minutes
    // =========================

    // 運動時間取得
    public Integer getExerciseMinutes() {
        return exerciseMinutes;
    }

    // 運動時間設定
    public void setExerciseMinutes(Integer exerciseMinutes) {
        this.exerciseMinutes = exerciseMinutes;
    }

    // =========================
    // Sleep Hours
    // =========================

    // 睡眠時間取得
    public Double getSleepHours() {
        return sleepHours;
    }

    // 睡眠時間設定
    public void setSleepHours(Double sleepHours) {
        this.sleepHours = sleepHours;
    }

    // =========================
    // Water Intake
    // =========================

    // 水分摂取量取得
    public Integer getWaterMl() {
        return waterMl;
    }

    // 水分摂取量設定
    public void setWaterMl(Integer waterMl) {
        this.waterMl = waterMl;
    }
}