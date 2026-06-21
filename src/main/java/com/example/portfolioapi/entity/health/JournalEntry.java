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
//
// Entity / Table / Id / Column / GeneratedValue など、
// DBテーブルとJavaクラスを紐付けるために使用
//
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

// =========================
// JournalEntry Entity
// =========================
//
// ジャーナリング記録を管理するEntity
//
// 対応テーブル
// journal_entries
//
// このクラスの1インスタンスが、
// journal_entries テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・ジャーナル記録日
// ・感謝したこと
// ・頑張ったこと
// ・明日の目標
// ・自由記述
//
// 健康と日記ページの
// ジャーナリング機能で利用する
//
@Entity
@Table(name = "journal_entries")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このJournalEntryクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、journal_entries テーブルは実質的に
//
// id
// user_id
// journal_date
// gratitude
// achievement
// tomorrow_goal
// free_text
// created_at
// updated_at
//
// のカラムを持つ
//
public class JournalEntry extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // journal_entries テーブルの主キー
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
    // このジャーナリング記録が
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
    // Journal Date
    // =========================
    //
    // ジャーナリング記録の日付
    //
    // 例:
    // 2026-05-31
    //
    // Java側:
    // journalDate
    //
    // DB側:
    // journal_date
    //
    @Column(name = "journal_date")
    private LocalDate journalDate;

    // =========================
    // Gratitude
    // =========================
    //
    // 感謝したこと
    //
    // 例:
    // 家族に感謝した
    //
    // DB型:
    // text
    //
    @Column(columnDefinition = "TEXT")
    private String gratitude;

    // =========================
    // Achievement
    // =========================
    //
    // 頑張ったこと
    //
    // 例:
    // 30分ジョギングした
    //
    // DB型:
    // text
    //
    @Column(columnDefinition = "TEXT")
    private String achievement;

    // =========================
    // Tomorrow Goal
    // =========================
    //
    // 明日の目標
    //
    // 例:
    // 読書を30分する
    //
    // Java側:
    // tomorrowGoal
    //
    // DB側:
    // tomorrow_goal
    //
    // DB型:
    // text
    //
    @Column(
            name = "tomorrow_goal",
            columnDefinition = "TEXT"
    )
    private String tomorrowGoal;

    // =========================
    // Free Text
    // =========================
    //
    // 自由記述
    //
    // 気づきや振り返りを記録
    //
    // Java側:
    // freeText
    //
    // DB側:
    // free_text
    //
    // DB型:
    // text
    //
    @Column(
            name = "free_text",
            columnDefinition = "TEXT"
    )
    private String freeText;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    public JournalEntry() {
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
    // Journal Date
    // =========================

    // ジャーナル記録日取得
    public LocalDate getJournalDate() {
        return journalDate;
    }

    // ジャーナル記録日設定
    public void setJournalDate(LocalDate journalDate) {
        this.journalDate = journalDate;
    }

    // =========================
    // Gratitude
    // =========================

    // 感謝したこと取得
    public String getGratitude() {
        return gratitude;
    }

    // 感謝したこと設定
    public void setGratitude(String gratitude) {
        this.gratitude = gratitude;
    }

    // =========================
    // Achievement
    // =========================

    // 頑張ったこと取得
    public String getAchievement() {
        return achievement;
    }

    // 頑張ったこと設定
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    // =========================
    // Tomorrow Goal
    // =========================

    // 明日の目標取得
    public String getTomorrowGoal() {
        return tomorrowGoal;
    }

    // 明日の目標設定
    public void setTomorrowGoal(String tomorrowGoal) {
        this.tomorrowGoal = tomorrowGoal;
    }

    // =========================
    // Free Text
    // =========================

    // 自由記述取得
    public String getFreeText() {
        return freeText;
    }

    // 自由記述設定
    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }
}