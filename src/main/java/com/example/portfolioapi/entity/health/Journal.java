package com.example.portfolioapi.entity.health;

// =========================
// Import
// =========================

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// =========================
// Journal Entity
// =========================
//
// ジャーナリング記録を管理するテーブル
//
// 対応テーブル
// journal
//
// 管理項目
//
// ・感謝したこと
// ・頑張ったこと
// ・明日の目標
// ・自由記述
//
// 健康と日記ページの
// ジャーナリング機能で利用する
//
@Entity
public class Journal {

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
    // 感謝したこと
    // =========================
    //
    // 例:
    // 家族に感謝した
    //
    @Column(length = 2000)
    private String gratitude;

    // =========================
    // 頑張ったこと
    // =========================
    //
    // 例:
    // 30分ジョギングした
    //
    @Column(length = 2000)
    private String achievement;

    // =========================
    // 明日の目標
    // =========================
    //
    // 例:
    // 読書を30分する
    //
    @Column(length = 2000)
    private String tomorrowGoal;

    // =========================
    // 自由記述
    // =========================
    //
    // 気づきや振り返りを記録
    //
    @Column(length = 5000)
    private String note;

    // =========================
    // Constructor
    // =========================

    public Journal() {
    }

    // =========================
    // Getter / Setter
    // =========================

    public String getId() {
        return id;
    }

    public void setId(
            String id
    ) {
        this.id = id;
    }

    // =========================
    // Date
    // =========================

    public String getDate() {
        return date;
    }

    public void setDate(
            String date
    ) {
        this.date = date;
    }

    // =========================
    // Gratitude
    // =========================

    public String getGratitude() {
        return gratitude;
    }

    public void setGratitude(
            String gratitude
    ) {
        this.gratitude = gratitude;
    }

    // =========================
    // Achievement
    // =========================

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(
            String achievement
    ) {
        this.achievement = achievement;
    }

    // =========================
    // Tomorrow Goal
    // =========================

    public String getTomorrowGoal() {
        return tomorrowGoal;
    }

    public void setTomorrowGoal(
            String tomorrowGoal
    ) {
        this.tomorrowGoal =
                tomorrowGoal;
    }

    // =========================
    // Note
    // =========================

    public String getNote() {
        return note;
    }

    public void setNote(
            String note
    ) {
        this.note = note;
    }
}