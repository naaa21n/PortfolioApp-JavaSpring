package com.example.portfolioapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Learning {

    @Id
    private String id;

    private String title;
    private boolean done;

    public Learning() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }
}