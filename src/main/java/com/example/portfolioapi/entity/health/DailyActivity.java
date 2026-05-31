package com.example.portfolioapi.entity.health;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// =========================
// Daily Activity Entity
// =========================
//
// 1日の行動記録
//
// 例
//
// ・睡眠
// ・仕事
// ・勉強
// ・運動
// ・読書
// ・食事
//
@Entity
public class DailyActivity {

    @Id
    private String id;

    // 記録日
    private String date;

    // 活動名
    private String activityName;

    // 活動時間(分)
    private Integer minutes;

    public DailyActivity() {
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
}