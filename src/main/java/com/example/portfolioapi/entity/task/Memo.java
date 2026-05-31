package com.example.portfolioapi.entity.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Memo {

    @Id
    private String id;

    // タイトル
    private String title;

    // 本文
    private String content;

    // カテゴリ
    private String category;

    // 作成日
    private String createdDate;

    public Memo() {
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

    public String getContent() {
        return content;
    }

    public void setContent(
            String content
    ) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(
            String category
    ) {
        this.category = category;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(
            String createdDate
    ) {
        this.createdDate = createdDate;
    }
}