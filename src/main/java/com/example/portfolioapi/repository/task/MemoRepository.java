package com.example.portfolioapi.repository.task;

// =========================
// Import
// =========================

// Memo Entity
import com.example.portfolioapi.entity.task.Memo;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.util.List;
import java.util.UUID;

// =========================
// Memo Repository
// =========================
//
// memos テーブル操作用Repository
//
// Memo Entity を使って、
// memos テーブルを操作する
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
//
// Memo の主キー id は UUID 型なので、
// 第2引数は String ではなく UUID にする
//
public interface MemoRepository
        extends JpaRepository<Memo, UUID> {

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーのメモ一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM memos
    // WHERE user_id = ?
    //
    List<Memo> findByUserId(
            UUID userId
    );

    // =========================
    // タイトル検索
    // =========================
    //
    // メモのタイトルで完全一致検索
    //
    // SQLイメージ:
    // SELECT * FROM memos
    // WHERE title = ?
    //
    List<Memo> findByTitle(
            String title
    );

    // =========================
    // タイトル部分一致検索
    // =========================
    //
    // メモのタイトルに指定キーワードが含まれるものを取得
    //
    // 例:
    // "開発" で検索すると
    // "開発メモ"
    // "Spring Boot開発"
    // などを取得できる
    //
    // SQLイメージ:
    // SELECT * FROM memos
    // WHERE title LIKE '%keyword%'
    //
    List<Memo> findByTitleContaining(
            String keyword
    );

    // =========================
    // ユーザーID + タイトル部分一致検索
    // =========================
    //
    // 指定したユーザーのメモから、
    // タイトルに指定キーワードが含まれるものを取得
    //
    // SQLイメージ:
    // SELECT * FROM memos
    // WHERE user_id = ?
    // AND title LIKE '%keyword%'
    //
    List<Memo> findByUserIdAndTitleContaining(
            UUID userId,
            String keyword
    );

    // =========================
    // 本文部分一致検索
    // =========================
    //
    // メモ本文に指定キーワードが含まれるものを取得
    //
    // SQLイメージ:
    // SELECT * FROM memos
    // WHERE content LIKE '%keyword%'
    //
    List<Memo> findByContentContaining(
            String keyword
    );

    // =========================
    // ユーザーID + 本文部分一致検索
    // =========================
    //
    // 指定したユーザーのメモから、
    // 本文に指定キーワードが含まれるものを取得
    //
    // SQLイメージ:
    // SELECT * FROM memos
    // WHERE user_id = ?
    // AND content LIKE '%keyword%'
    //
    List<Memo> findByUserIdAndContentContaining(
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