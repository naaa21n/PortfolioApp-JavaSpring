package com.example.portfolioapi.repository;

// Userエンティティ
import com.example.portfolioapi.entity.User;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// User Repository
// =========================

// Repositoryインターフェース
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
public interface UserRepository
        extends JpaRepository<User, String> {

    // =========================
    // email検索
    // =========================

    // emailを使ってユーザー検索
    //
    // SQLイメージ:
    // SELECT * FROM "user"
    // WHERE email = ?
    //
    // ログイン時や重複チェックで使用
    User findByEmail(String email);

    // =========================
    // JpaRepositoryで自動利用可能
    // =========================

    // save()
    // -> 保存 / 更新
    //
    // findAll()
    // -> 全件取得
    //
    // findById()
    // -> ID検索
    //
    // deleteById()
    // -> ID削除
    //
    // count()
    // -> 件数取得

    // =========================
    // 今後追加できる検索例
    // =========================

    // User findByName(String name);
    //
    // List<User> findByNameContaining(String keyword);
}