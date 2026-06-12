package com.example.portfolioapi.entity.user;

// 共通カラム用のBaseEntityを継承するため
import com.example.portfolioapi.entity.common.BaseEntity;
// JPA(Entity)用
import jakarta.persistence.*;
import java.util.UUID;

// =========================
// BaseEntity 継承
// =========================
//
// BaseEntityを継承することで、
// Userクラスには直接記載していない以下の共通カラムも
// usersテーブルに自動で含まれる
//
// ・created_at
// ・updated_at
//
// つまり、このUser Entityは実質的に
//
// id
// name
// email
// password
// created_at
// updated_at
//
// のカラムを持つテーブルとして扱われる
//

// =========================
// User Entity
// =========================

// このクラスをDBテーブルとして扱う
@Entity

// user はSQL予約語のため
// 明示的に "user" テーブル名を指定
@Table(name = "users")
public class User extends BaseEntity {

    // =========================
    // Primary Key
    // =========================

    // usersテーブルの主キー
    //
    // UUID形式で自動生成
    //
    // 例:
    // 550e8400-e29b-41d4-a716-446655440000
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    // 継承される共通カラム
    // =========================
    //
    // 以下の項目はこのUserクラス内には直接書かない
    //
    // private LocalDateTime createdAt;
    // private LocalDateTime updatedAt;
    //
    // これらは BaseEntity から継承される
    //
    // createdAt:
    // 新規登録時に自動設定される
    //
    // updatedAt:
    // 新規登録時・更新時に自動設定される
    //
    // そのため、ControllerやServiceで
    // setCreatedAt(...)
    // setUpdatedAt(...)
    // のように手動設定する必要はない
    //

    // =========================
    // Default Constructor
    // =========================

    // JPAで必要な空コンストラクタ
    public User() {}

    // =========================
    // Getter / Setter
    // =========================

    // ID取得
    public UUID getId() {
        return id;
    }

    // ID設定
    public void setId(UUID id) {
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