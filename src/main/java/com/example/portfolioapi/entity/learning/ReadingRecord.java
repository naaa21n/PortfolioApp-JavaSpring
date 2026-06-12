package com.example.portfolioapi.entity.learning;

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

import java.time.LocalDate;
import java.util.UUID;

// =========================
// ReadingRecord Entity
// =========================
//
// 読書記録を管理するEntity
//
// 対応テーブル
// reading_records
//
// このクラスの1インスタンスが、
// reading_records テーブルの1行分のデータを表す
//
// 管理項目
//
// ・ユーザーID
// ・本のタイトル
// ・読書時間
// ・読書日
// ・画像URL
//
// 読書管理ページや
// 読書グラフ機能で利用する
//
@Entity
@Table(name = "reading_records")

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// このReadingRecordクラスには直接書いていない
// 以下の共通カラムも自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、reading_records テーブルは実質的に
//
// id
// user_id
// book_title
// reading_minutes
// read_on
// image_url
// created_at
// updated_at
//
// のカラムを持つ
//
public class ReadingRecord extends BaseEntity {

    // =========================
    // Primary Key
    // =========================
    //
    // reading_records テーブルの主キー
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
    // この読書記録が
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
    // Book Title
    // =========================
    //
    // 本のタイトル
    //
    // 例:
    // "7つの習慣"
    // "エッセンシャル思考"
    //
    // Java側:
    // bookTitle
    //
    // DB側:
    // book_title
    //
    @Column(name = "book_title")
    private String bookTitle;

    // =========================
    // Reading Minutes
    // =========================
    //
    // 読書時間
    //
    // 単位:
    // 分
    //
    // 例:
    // 30
    // 60
    // 120
    //
    // Java側:
    // readingMinutes
    //
    // DB側:
    // reading_minutes
    //
    @Column(name = "reading_minutes")
    private Integer readingMinutes;

    // =========================
    // Read On
    // =========================
    //
    // 読書日
    //
    // 例:
    // 2026-06-12
    //
    // Java側:
    // readOn
    //
    // DB側:
    // read_on
    //
    @Column(name = "read_on")
    private LocalDate readOn;

    // =========================
    // Image URL
    // =========================
    //
    // 本の画像URL
    //
    // 例:
    // https://example.com/book-image.png
    //
    // Java側:
    // imageUrl
    //
    // DB側:
    // image_url
    //
    @Column(name = "image_url")
    private String imageUrl;

    // =========================
    // Default Constructor
    // =========================
    //
    // JPAがEntityを生成するときに必要
    //
    // DBから取得したデータを
    // ReadingRecordオブジェクトに変換するときなどに使われる
    //
    public ReadingRecord() {
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
    // Book Title
    // =========================

    // 本のタイトル取得
    public String getBookTitle() {
        return bookTitle;
    }

    // 本のタイトル設定
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    // =========================
    // Reading Minutes
    // =========================

    // 読書時間取得
    public Integer getReadingMinutes() {
        return readingMinutes;
    }

    // 読書時間設定
    public void setReadingMinutes(Integer readingMinutes) {
        this.readingMinutes = readingMinutes;
    }

    // =========================
    // Read On
    // =========================

    // 読書日取得
    public LocalDate getReadOn() {
        return readOn;
    }

    // 読書日設定
    public void setReadOn(LocalDate readOn) {
        this.readOn = readOn;
    }

    // =========================
    // Image URL
    // =========================

    // 画像URL取得
    public String getImageUrl() {
        return imageUrl;
    }

    // 画像URL設定
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}