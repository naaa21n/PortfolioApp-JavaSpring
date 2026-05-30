package com.example.portfolioapi.entity;

// JPA(Entity)用
import jakarta.persistence.*;

// =========================
// User Entity
// =========================

// このクラスをDBテーブルとして扱う
@Entity

// user はSQL予約語のため
// 明示的に "user" テーブル名を指定
@Table(name = "\"user\"")
public class User {

    // =========================
    // Primary Key
    // =========================

    // テーブルの主キー
    @Id

    // ID自動採番
    //
    // PostgreSQLの
    // SERIAL / IDENTITY 相当
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    // =========================
    // ユーザー名
    // =========================

    // 例:
    // "taro"
    // "hanako"
    private String name;

    // =========================
    // メールアドレス
    // =========================

    // unique=true
    //
    // DB上で重複禁止
    //
    // 同じemail登録不可
    @Column(unique = true)
    private String email;

    // =========================
    // パスワード
    // =========================

    // BCryptハッシュ済み
    // パスワード保存用
    //
    // 例:
    // $2a$10$xxxxx...
    private String password;

    // =========================
    // Default Constructor
    // =========================

    // JPAで必要な空コンストラクタ
    public User() {}

    // =========================
    // Getter / Setter
    // =========================

    // ID取得
    public Long getId() {
        return id;
    }

    // ID設定
    public void setId(Long id) {
        this.id = id;
    }

    // name取得
    public String getName() {
        return name;
    }

    // name設定
    public void setName(String name) {
        this.name = name;
    }

    // email取得
    public String getEmail() {
        return email;
    }

    // email設定
    public void setEmail(String email) {
        this.email = email;
    }

    // password取得
    public String getPassword() {
        return password;
    }

    // password設定
    public void setPassword(String password) {
        this.password = password;
    }
}