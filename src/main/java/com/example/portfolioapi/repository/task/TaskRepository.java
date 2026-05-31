package com.example.portfolioapi.repository.task;

// Taskエンティティ
import com.example.portfolioapi.entity.task.Task;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// =========================
// Task Repository
// =========================

// Repositoryインターフェース
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
public interface TaskRepository
        extends JpaRepository<Task, String> {

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
    // List<Task> findByDone(boolean done);
    //
    // List<Task> findByTitle(String title);
    //
    // List<Task> findByCreatedAt(LocalDateTime createdAt);
}