package com.example.portfolioapi.entity.health;

// =========================
// Import
// =========================

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// =========================
// Activity Entity
// =========================
//
// 1日の活動時間を管理するテーブル
//
// 対応テーブル
// activity
//
// 管理項目
//
// ・睡眠時間
// ・仕事時間
// ・勉強時間
// ・運動時間
// ・趣味時間
// ・その他時間
//
// 円グラフ表示や
// 行動ログ機能で利用する
//
@Entity
public class Activity {

    // =========================
    // Primary Key
    // =========================

    @Id
    private String id;

    // =========================
    // 活動日
    // =========================
    //
    // 例:
    // 2026-05-31
    //
    private String date;

    // =========================
    // 睡眠時間
    // =========================
    private Integer sleep;

    // =========================
    // 仕事時間
    // =========================
    private Integer work;

    // =========================
    // 勉強時間
    // =========================
    private Integer study;

    // =========================
    // 運動時間
    // =========================
    private Integer exercise;

    // =========================
    // 趣味時間
    // =========================
    private Integer hobby;

    // =========================
    // その他時間
    // =========================
    private Integer other;

    // =========================
    // Constructor
    // =========================

    public Activity() {
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

    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    public Integer getStudy() {
        return study;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    public Integer getExercise() {
        return exercise;
    }

    public void setExercise(Integer exercise) {
        this.exercise = exercise;
    }

    public Integer getHobby() {
        return hobby;
    }

    public void setHobby(Integer hobby) {
        this.hobby = hobby;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }
}