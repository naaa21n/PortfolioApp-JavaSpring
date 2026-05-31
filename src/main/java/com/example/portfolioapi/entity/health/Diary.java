package com.example.portfolioapi.entity.health;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Diary {

    // =========================
    // Primary Key
    // =========================

    @Id
    private String id;

    // =========================
    // Diary Data
    // =========================

    // 日付
    private String date;

    // 日記内容
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