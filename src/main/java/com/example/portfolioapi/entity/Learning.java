package com.example.portfolioapi.entity;

// JPA(Entity)用
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// =========================
// Learning Entity
// =========================

// このクラスをDBテーブルとして扱う
@Entity

// DBテーブル名
// learning テーブルへ紐付け
@Table(name = "learning")
public class Learning {

    // =========================
    // Primary Key
    // =========================

    // テーブルの主キー
    // 一意のID
    @Id
    private String id;

    // =========================
    // 学習タイトル
    // =========================

    // 例:
    // "Spring Boot"
    // "英語学習"
    // "React勉強"
    private String title;

    // =========================
    // 学習時間
    // =========================

    // 学習時間(hours)
    //
    // 例:
    // 1
    // 2
    // 5
    private int studyHours;

    // =========================
    // 学習日
    // =========================

    // 学習日
    //
    // 例:
    // "2025-05-29"
    private String studyDate;

    // =========================
    // 完了状態
    // =========================

    // true  -> 完了
    // false -> 未完了
    private boolean done;

    // =========================
    // Default Constructor
    // =========================

    // JPAで必要な空コンストラクタ
    public Learning() {
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

    // studyHours取得
    public int getStudyHours() {
        return studyHours;
    }

    // studyHours設定
    public void setStudyHours(int studyHours) {
        this.studyHours = studyHours;
    }

    // studyDate取得
    public String getStudyDate() {
        return studyDate;
    }

    // studyDate設定
    public void setStudyDate(String studyDate) {
        this.studyDate = studyDate;
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
}