package com.example.portfolioapi.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// =========================
// Base Entity
// =========================
//
// 各テーブル共通のカラムを定義するクラス
//
// created_at
// updated_at
//
// を各Entityへ継承させる
//
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // =========================
    // 作成日時
    // =========================
    //
    // INSERT時に自動設定される
    //
    // updatable = false
    // → 更新時に変更されない
    //
    @CreatedDate
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    // =========================
    // 更新日時
    // =========================
    //
    // INSERT時にも設定される
    // UPDATE時にも自動更新される
    //
    @LastModifiedDate
    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    // =========================
    // Getter
    // =========================
    //
    // createdAt / updatedAt は
    // 基本的にSpringが自動設定するため
    // Setterは作らなくてもよい
    //

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}