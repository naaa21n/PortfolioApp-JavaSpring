package com.example.portfolioapi.repository.task;

// =========================
// Import
// =========================

// Task Entity
import com.example.portfolioapi.entity.task.Task;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

// =========================
// Task Repository
// =========================
//
// tasks テーブル操作用Repository
//
// Task Entity を使って、
// tasks テーブルを操作する
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
//
// Task の主キー id は UUID 型なので、
// 第2引数は String ではなく UUID にする
//
public interface TaskRepository
        extends JpaRepository<Task, UUID> {

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーのタスク一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    //
    List<Task> findByUserId(
            UUID userId
    );

    // =========================
    // タスク日検索
    // =========================
    //
    // 指定したタスク日のタスク一覧を取得
    //
    // Entity側のフィールド名は taskDate なので、
    // findByTaskDate にする
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE task_date = ?
    //
    List<Task> findByTaskDate(
            LocalDate taskDate
    );

    // =========================
    // ユーザーID + タスク日検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した日付のタスク一覧を取得
    //
    // 1日に複数タスクがありえるため、
    // Optional ではなく List にする
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND task_date = ?
    //
    List<Task> findByUserIdAndTaskDate(
            UUID userId,
            LocalDate taskDate
    );

    // =========================
    // ユーザーID + タスク日期間検索
    // =========================
    //
    // 指定したユーザーのタスクを
    // 指定期間で取得
    //
    // カレンダー表示や週間表示で便利
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND task_date BETWEEN ? AND ?
    //
    List<Task> findByUserIdAndTaskDateBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );

    // =========================
    // 締切日検索
    // =========================
    //
    // 指定した締切日のタスク一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE deadline = ?
    //
    List<Task> findByDeadline(
            LocalDate deadline
    );

    // =========================
    // ユーザーID + 締切日検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した締切日のタスク一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND deadline = ?
    //
    List<Task> findByUserIdAndDeadline(
            UUID userId,
            LocalDate deadline
    );

    // =========================
    // ユーザーID + 締切日期間検索
    // =========================
    //
    // 指定したユーザーのタスクを
    // 締切日の期間で取得
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND deadline BETWEEN ? AND ?
    //
    List<Task> findByUserIdAndDeadlineBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );

    // =========================
    // 完了状態検索
    // =========================
    //
    // 完了 / 未完了 のタスク一覧を取得
    //
    // Entity側のフィールド名は done ではなく completed なので、
    // findByDone ではなく findByCompleted にする
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE completed = ?
    //
    List<Task> findByCompleted(
            boolean completed
    );

    // =========================
    // ユーザーID + 完了状態検索
    // =========================
    //
    // 指定したユーザーの、
    // 完了 / 未完了 タスク一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND completed = ?
    //
    List<Task> findByUserIdAndCompleted(
            UUID userId,
            boolean completed
    );

    // =========================
    // ユーザーID + タスク日 + 完了状態検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した日付の、
    // 完了 / 未完了 タスク一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND task_date = ?
    // AND completed = ?
    //
    List<Task> findByUserIdAndTaskDateAndCompleted(
            UUID userId,
            LocalDate taskDate,
            boolean completed
    );

    // =========================
    // タイトル検索
    // =========================
    //
    // タスク名で部分一致検索
    //
    // 例:
    // "Spring" で検索すると
    // "Spring Bootを勉強する"
    // などを取得できる
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE title LIKE '%keyword%'
    //
    List<Task> findByTitleContaining(
            String keyword
    );

    // =========================
    // ユーザーID + タイトル検索
    // =========================
    //
    // 指定したユーザーのタスクから、
    // タイトル部分一致で検索
    //
    // SQLイメージ:
    // SELECT * FROM tasks
    // WHERE user_id = ?
    // AND title LIKE '%keyword%'
    //
    List<Task> findByUserIdAndTitleContaining(
            UUID userId,
            String keyword
    );

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
}