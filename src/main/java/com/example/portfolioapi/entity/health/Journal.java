package com.example.portfolioapi.entity.health;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// =========================
// Journal Entity
// =========================
//
// ジャーナリング記録
//
// ・感謝したこと
// ・頑張ったこと
// ・明日の目標
// ・自由記述
//
@Entity
public class Journal {

    @Id
    private String id;

    // 記録日
    private String date;

    // 感謝したこと
    @Column(length = 2000)
    private String gratitude;

    // 頑張ったこと
    @Column(length = 2000)
    private String achievement;

    // 明日の目標
    @Column(length = 2000)
    private String tomorrowGoal;

    // 自由記述
    @Column(length = 5000)
    private String note;

    public Journal() {
    }

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

    public String getGratitude() {
        return gratitude;
    }

    public void setGratitude(String gratitude) {
        this.gratitude = gratitude;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getTomorrowGoal() {
        return tomorrowGoal;
    }

    public void setTomorrowGoal(String tomorrowGoal) {
        this.tomorrowGoal = tomorrowGoal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}