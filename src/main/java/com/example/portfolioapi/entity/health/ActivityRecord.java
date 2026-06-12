package com.example.portfolioapi.entity.health;

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

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

// =========================
// ActivityRecord Entity
// =========================
//
// 1日の活動時間を管理するEntity
//
// 対応テーブル
// activity_records
//
// このクラスの1インスタンスが、
// activity_records テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・記録日
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
@Table(name = "activity_records")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このActivityRecordクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、activity_records テーブルは実質的に
//
// id
// user_id
// record_date
// sleep
// work
// study
// exercise
// hobby
// other
// created_at
// updated_at
//
// のカラムを持つ
//
public class ActivityRecord extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // activity_records テーブルの主キー
    //
    // UUID形式で自動生成される
    //
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // =========================
    // User ID
    // =========================
    //
    // この活動記録が
    // どのユーザーに紐づくデータかを表す
    //
    // DBカラム名:
    // user_id
    //
    // usersテーブルの id を参照する想定
    //
    @Column(name = "user_id")
    private UUID userId;

    // =========================
    // Record Date
    // =========================
    //
    // 活動記録の日付
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
    // Sleep
    // =========================
    //
    // 睡眠時間
    //
    // 単位:
    // 分
    //
    // 例:
    // 420 = 7時間
    //
    private Integer sleep;

    // =========================
    // Work
    // =========================
    //
    // 仕事時間
    //
    // 単位:
    // 分
    //
    private Integer work;

    // =========================
    // Study
    // =========================
    //
    // 勉強時間
    //
    // 単位:
    // 分
    //
    private Integer study;

    // =========================
    // Exercise
    // =========================
    //
    // 運動時間
    //
    // 単位:
    // 分
    //
    private Integer exercise;

    // =========================
    // Hobby
    // =========================
    //
    // 趣味時間
    //
    // 単位:
    // 分
    //
    private Integer hobby;

    // =========================
    // Other
    // =========================
    //
    // その他時間
    //
    // 単位:
    // 分
    //
    private Integer other;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    public ActivityRecord() {
    }

    // =========================
    // Getter / Setter
    // =========================

    // =========================
    // ID
    // =========================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // =========================
    // User ID
    // =========================

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    // =========================
    // Record Date
    // =========================

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    // =========================
    // Sleep
    // =========================

    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    // =========================
    // Work
    // =========================

    public Integer getWork() {
        return work;
    }

    public void setWork(Integer work) {
        this.work = work;
    }

    // =========================
    // Study
    // =========================

    public Integer getStudy() {
        return study;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    // =========================
    // Exercise
    // =========================

    public Integer getExercise() {
        return exercise;
    }

    public void setExercise(Integer exercise) {
        this.exercise = exercise;
    }

    // =========================
    // Hobby
    // =========================

    public Integer getHobby() {
        return hobby;
    }

    public void setHobby(Integer hobby) {
        this.hobby = hobby;
    }

    // =========================
    // Other
    // =========================

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }
}