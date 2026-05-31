package com.example.portfolioapi.entity.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Schedule {

    @Id
    private String id;

    // 予定名
    private String title;

    // 日付
    private String date;

    // 開始時刻
    private String startTime;

    // 終了時刻
    private String endTime;

    // メモ
    private String memo;

    public Schedule() {
    }

    public String getId() {
        return id;
    }

    public void setId(
            String id
    ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(
            String title
    ) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(
            String date
    ) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(
            String startTime
    ) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(
            String endTime
    ) {
        this.endTime = endTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(
            String memo
    ) {
        this.memo = memo;
    }
}