package com.example.portfolioapi.repository.user;

// Userエンティティ
import com.example.portfolioapi.entity.user.User;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Optional
import java.util.Optional;

// UUID
import java.util.UUID;

// =========================
// User Repository
// =========================

// Repositoryインターフェース
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
//
// Userエンティティの主キーは UUID 型なので、
// 第2引数は String ではなく UUID にする
public interface UserRepository
        extends JpaRepository<User, UUID> {

    // =========================
    // email検索
    // =========================

    // emailを使ってユーザー検索
    //
    // SQLイメージ:
    // SELECT * FROM users
    // WHERE email = ?
    //
    // ログイン時に使用
    //
    // Optional<User> にすることで、
    // 見つからなかった場合を安全に扱える
    Optional<User> findByEmail(String email);

    // =========================
    // email重複チェック
    // =========================

    // 指定したemailが既に存在するか確認する
    //
    // SQLイメージ:
    // SELECT COUNT(*) FROM users
    // WHERE email = ?
    //
    // 会員登録時の重複チェックで使用
    boolean existsByEmail(String email);

    // =========================
    // JpaRepositoryで自動利用可能
    // =========================

    // save()
    // -> 保存 / 更新
    //
    // findAll()
    // -> 全件取得
    //
    // findById(UUID id)
    // -> ID検索
    //
    // deleteById(UUID id)
    // -> ID削除
    //
    // count()
    // -> 件数取得

    // =========================
    // 今後追加できる検索例
    // =========================

    // Optional<User> findByName(String name);
    // List<User> findByNameContaining(String keyword);
}