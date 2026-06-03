package com.example.portfolioapi.entity.health;

// =========================
// Import
// =========================

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// =========================
// Daily Activity Entity
// =========================
//
// 1日の活動履歴を管理するテーブル
//
// 対応テーブル
// daily_activity
//
// 管理項目
//
// ・睡眠
// ・仕事
// ・勉強
// ・運動
// ・読書
// ・食事
//
// タイムライン形式の
// 行動履歴表示で利用する
//
@Entity
public class DailyActivity {

    // =========================
    // Primary Key
    // =========================

    @Id
    private String id;

    // =========================
    // 記録日
    // =========================
    //
    // 例:
    // 2026-05-31
    //
    private String date;

    // =========================
    // 活動名
    // =========================
    //
    // 例:
    // 睡眠
    // 勉強
    // 運動
    // 読書
    //
    private String activityName;

    // =========================
    // 活動時間
    // =========================
    //
    // 単位: 分
    //
    // 例:
    // 30
    // 60
    // 120
    //
    private Integer minutes;

    // =========================
    // Constructor
    // =========================

    public DailyActivity() {
    }

    // =========================
    // Getter / Setter
    // =========================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(
            String activityName
    ) {
        this.activityName =
                activityName;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(
            Integer minutes
    ) {
        this.minutes = minutes;
    }
}