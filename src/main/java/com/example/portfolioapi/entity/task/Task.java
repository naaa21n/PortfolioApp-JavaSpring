package com.example.portfolioapi.entity.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    private String id;

    // タスク名
    private String title;

    // 詳細
    private String description;

    // 締切日
    private String dueDate;

    // 優先度
    private Integer priority;

    // 完了状態
    private boolean done;

    public Task() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(
            String description
    ) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(
            String dueDate
    ) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(
            Integer priority
    ) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(
            boolean done
    ) {
        this.done = done;
    }
}