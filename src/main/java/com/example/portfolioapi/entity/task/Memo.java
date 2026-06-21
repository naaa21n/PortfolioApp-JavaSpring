package com.example.portfolioapi.entity.task;

// =========================
// Common Entity Import
// =========================
//
// created_at / updated_at を継承するため
//
import com.example.portfolioapi.entity.common.BaseEntity;

// =========================
// JPA Import
// =========================
//
// Entity / Table / Id / Column / GeneratedValue など、
// DBテーブルとJavaクラスを紐付けるために使用
//
import jakarta.persistence.*;

import java.util.UUID;

// =========================
// Memo Entity
// =========================
//
// メモ情報を管理するEntity
//
// 対応テーブル
// memos
//
// このクラスの1インスタンスが、
// memos テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・タイトル
// ・本文
//
// メモ管理機能で利用する
//
@Entity
@Table(name = "memos")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このMemoクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、memos テーブルは実質的に
//
// id
// user_id
// title
// content
// created_at
// updated_at
//
// のカラムを持つ
//
public class Memo extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // memos テーブルの主キー
    //
    // UUID形式で自動生成される
    //
    // 例:
    // 550e8400-e29b-41d4-a716-446655440000
    //
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // =========================
    // User ID
    // =========================
    //
    // このメモが
    // どのユーザーに紐づくデータかを表す
    //
    // DBカラム名:
    // user_id
    //
    // usersテーブルの id を参照する想定
    //
    // Java側:
    // userId
    //
    // DB側:
    // user_id
    //
    @Column(name = "user_id")
    private UUID userId;

    // =========================
    // Title
    // =========================
    //
    // メモのタイトル
    //
    // 例:
    // "買い物メモ"
    // "開発メモ"
    //
    // DBカラム名はJava変数名と同じ title なので
    // @Column(name = "title") は省略している
    //
    private String title;

    // =========================
    // Content
    // =========================
    //
    // メモ本文
    //
    // 長文保存用
    //
    // DB型:
    // text
    //
    @Column(columnDefinition = "TEXT")
    private String content;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    // DBから取得したデータを
    // Memoオブジェクトに変換するときなどに使われる
    //
    public Memo() {
    }

    // =========================
    // Getter / Setter
    // =========================
    //
    // privateフィールドは外部クラスから直接アクセスできないため、
    // Getter / Setter 経由で値を取得・設定する
    //

    // =========================
    // ID
    // =========================

    // ID取得
    public UUID getId() {
        return id;
    }

    // ID設定
    //
    // 通常、idはUUIDで自動生成されるため、
    // アプリ側で手動設定する場面は少ない
    //
    public void setId(UUID id) {
        this.id = id;
    }

    // =========================
    // User ID
    // =========================

    // ユーザーID取得
    public UUID getUserId() {
        return userId;
    }

    // ユーザーID設定
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    // =========================
    // Title
    // =========================

    // タイトル取得
    public String getTitle() {
        return title;
    }

    // タイトル設定
    public void setTitle(String title) {
        this.title = title;
    }

    // =========================
    // Content
    // =========================

    // 本文取得
    public String getContent() {
        return content;
    }

    // 本文設定
    public void setContent(String content) {
        this.content = content;
    }
}