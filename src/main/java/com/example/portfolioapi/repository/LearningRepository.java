package com.example.portfolioapi.repository;

// Learningエンティティ
import com.example.portfolioapi.entity.Learning;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// Learning Repository
// =========================

// Repositoryインターフェース
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
public interface LearningRepository
        extends JpaRepository<Learning, String> {

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
    // 今は追加メソッドなし
    // =========================

    // 必要になったら独自検索追加可能
    //
    // 例:
    //
    // List<Learning> findByDone(boolean done);
    //
    // List<Learning> findByStudyDate(String studyDate);
    //
    // List<Learning> findByTitle(String title);
}