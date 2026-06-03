package com.example.portfolioapi.entity.health;

// =========================
// JPA Import
// =========================

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

// =========================
// Health Entity
// =========================
//
// 健康記録を管理するテーブル
//
// 対応テーブル
// health
//
// 管理項目
//
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
public class Health {

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
    private LocalDate date;

    // =========================
    // 歩数
    // =========================
    //
    // 単位: 歩
    //
    // 例:
    // 5000
    // 10000
    //
    private Integer steps;

    // =========================
    // 運動時間
    // =========================
    //
    // 単位: 分
    //
    // 例:
    // 30
    // 60
    //
    private Integer exerciseMinutes;

    // =========================
    // 睡眠時間
    // =========================
    //
    // 単位: 時間
    //
    // 例:
    // 6.5
    // 7.0
    // 8.0
    //
    private Double sleepHours;

    // =========================
    // 水分摂取量
    // =========================
    //
    // 単位: ml
    //
    // 例:
    // 1500
    // 2000
    //
    private Integer waterMl;

    // =========================
    // Constructor
    // =========================

    public Health() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(
            LocalDate date
    ) {
        this.date = date;
    }

    // =========================
    // Steps
    // =========================

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(
            Integer steps
    ) {
        this.steps = steps;
    }

    // =========================
    // Exercise Minutes
    // =========================

    public Integer getExerciseMinutes() {
        return exerciseMinutes;
    }

    public void setExerciseMinutes(
            Integer exerciseMinutes
    ) {
        this.exerciseMinutes =
                exerciseMinutes;
    }

    // =========================
    // Sleep Hours
    // =========================

    public Double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(
            Double sleepHours
    ) {
        this.sleepHours =
                sleepHours;
    }

    // =========================
    // Water Intake
    // =========================

    public Integer getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(
            Integer waterMl
    ) {
        this.waterMl = waterMl;
    }
}