package com.example.portfolioapi.entity.health;

// =========================
// Import
// =========================

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// =========================
// Diary Entity
// =========================
//
// 日記データを管理するテーブル
//
// 対応テーブル
// diary
//
// 管理項目
//
// ・日付
// ・日記本文
//
// 健康と日記ページの
// 日記機能で利用する
//
@Entity
public class Diary {

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
    // 日記本文
    // =========================
    //
    // 長文保存用
    //
    // 最大5000文字
    //
    @Column(length = 5000)
    private String content;

    // =========================
    // Constructor
    // =========================

    public Diary() {
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

    public String getDate() {
        return date;
    }

    public void setDate(
            String date
    ) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
            String content
    ) {
        this.content = content;
    }
}