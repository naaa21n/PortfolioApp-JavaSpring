package com.example.portfolioapi.entity;

// JPA(Entity)用
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

// 日時用
import java.time.LocalDateTime;

// =========================
// Task Entity
// =========================

// このクラスをDBテーブルとして扱う
@Entity
public class Task {

    // =========================
    // Primary Key
    // =========================

    // テーブルの主キー
    // 一意のID
    @Id
    private String id;

    // =========================
    // タスクタイトル
    // =========================

    // 例:
    // "買い物"
    // "Spring勉強"
    // "筋トレ"
    private String title;

    // =========================
    // 完了状態
    // =========================

    // true  -> 完了
    // false -> 未完了
    private boolean done;

    // =========================
    // 作成日時
    // =========================

    // タスク作成日時
    //
    // 例:
    // 2025-05-29T12:00:00
    private LocalDateTime createdAt;

    // =========================
    // Default Constructor
    // =========================

    // JPAで必要な空コンストラクタ
    public Task() {
    }

    // =========================
    // Constructor
    // =========================

    // Task生成時に
    // id と title を設定
    public Task(
            String id,
            String title
    ) {

        this.id = id;
        this.title = title;
    }

    // =========================
    // 自動初期化
    // =========================

    // DB保存直前に自動実行される
    @PrePersist
    public void onCreate() {

        // 初期状態は未完了
        this.done = false;

        // 現在時刻を作成日時へ設定
        this.createdAt =
                LocalDateTime.now();
    }

    // =========================
    // Getter / Setter
    // =========================

    // ID取得
    public String getId() {
        return id;
    }

    // ID設定
    public void setId(String id) {
        this.id = id;
    }

    // title取得
    public String getTitle() {
        return title;
    }

    // title設定
    public void setTitle(String title) {
        this.title = title;
    }

    // done取得
    //
    // booleanはisXXX命名
    public boolean isDone() {
        return done;
    }

    // done設定
    public void setDone(boolean done) {
        this.done = done;
    }

    // createdAt取得
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // createdAt設定
    public void setCreatedAt(
            LocalDateTime createdAt
    ) {

        this.createdAt = createdAt;
    }
}