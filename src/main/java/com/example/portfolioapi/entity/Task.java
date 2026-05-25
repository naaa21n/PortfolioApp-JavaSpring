package com.example.portfolioapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    private String id;

    private String title;

    // 追加①：完了状態
    private boolean done;

    // 追加②：作成日時
    private LocalDateTime createdAt;

    public Task() {
    }

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    // 自動で初期値を入れる
    @PrePersist
    public void onCreate() {
        this.done = false;
        this.createdAt = LocalDateTime.now();
    }

    // getter / setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}