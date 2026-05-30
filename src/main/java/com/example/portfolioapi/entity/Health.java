package com.example.portfolioapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Health {

    @Id
    private String id;

    // 日付
    private String date;

    // 歩数
    private Integer steps;

    // 運動時間(分)
    private Integer exerciseMinutes;

    // 睡眠時間
    private Double sleepHours;

    // 水分摂取量(ml)
    private Integer waterMl;

    // 日記
    @Column(length = 2000)
    private String diary;

    // 感謝したこと
    @Column(length = 2000)
    private String gratitude;

    // 頑張ったこと
    @Column(length = 2000)
    private String achievement;

    // 明日の目標
    @Column(length = 2000)
    private String tomorrowGoal;

    public Health() {
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

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
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
}