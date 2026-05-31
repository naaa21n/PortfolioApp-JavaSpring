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
// 健康記録テーブル
//
// 主な管理項目
//
// ・日付
// ・歩数
// ・運動時間
// ・睡眠時間
// ・水分摂取量
//
@Entity
public class Health {

    // =========================
    // Primary Key
    // =========================

    @Id
    private String id;

    // =========================
    // Health Data
    // =========================

    // 記録日
    private LocalDate date;

    // 歩数
    private Integer steps;

    // 運動時間(分)
    private Integer exerciseMinutes;

    // 睡眠時間(時間)
    private Double sleepHours;

    // 水分摂取量(ml)
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

    public void setId(String id) {
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


    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getExerciseMinutes() {
        return exerciseMinutes;
    }

    public void setExerciseMinutes(Integer exerciseMinutes) {
        this.exerciseMinutes = exerciseMinutes;
    }

    public Double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(Double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public Integer getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(Integer waterMl) {
        this.waterMl = waterMl;
    }
}