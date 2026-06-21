package com.example.portfolioapi.repository.learning;

// =========================
// Import
// =========================

// ReadingRecord Entity
import com.example.portfolioapi.entity.learning.ReadingRecord;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

// =========================
// ReadingRecord Repository
// =========================
//
// reading_records テーブル操作用Repository
//
// ReadingRecord Entity を使って、
// reading_records テーブルを操作する
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
//
// ReadingRecord の主キー id は UUID 型なので、
// 第2引数は String ではなく UUID にする
//
public interface ReadingRecordRepository
        extends JpaRepository<ReadingRecord, UUID> {

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーの読書記録一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM reading_records
    // WHERE user_id = ?
    //
    List<ReadingRecord> findByUserId(
            UUID userId
    );

    // =========================
    // 読書日検索
    // =========================
    //
    // 指定した読書日の読書記録を取得
    //
    // Entity側のフィールド名は readDate ではなく readOn なので、
    // findByReadDate ではなく findByReadOn にする
    //
    // SQLイメージ:
    // SELECT * FROM reading_records
    // WHERE read_on = ?
    //
    List<ReadingRecord> findByReadOn(
            LocalDate readOn
    );

    // =========================
    // ユーザーID + 読書日検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した読書日の読書記録を取得
    //
    // 1日に複数の読書記録がありえるため、
    // Optional ではなく List にする
    //
    // 例:
    // 午前: 7つの習慣 30分
    // 夜: エッセンシャル思考 45分
    //
    // SQLイメージ:
    // SELECT * FROM reading_records
    // WHERE user_id = ?
    // AND read_on = ?
    //
    List<ReadingRecord> findByUserIdAndReadOn(
            UUID userId,
            LocalDate readOn
    );

    // =========================
    // ユーザーID + 期間検索
    // =========================
    //
    // 指定したユーザーの読書記録を
    // 指定期間で取得
    //
    // 週間グラフ、月間グラフ、カレンダー表示で便利
    //
    // SQLイメージ:
    // SELECT * FROM reading_records
    // WHERE user_id = ?
    // AND read_on BETWEEN ? AND ?
    //
    List<ReadingRecord> findByUserIdAndReadOnBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );

    // =========================
    // 本のタイトル検索
    // =========================
    //
    // 本のタイトルで部分一致検索
    //
    // 例:
    // "習慣" で検索すると
    // "7つの習慣"
    // などを取得できる
    //
    // Entity側のフィールド名は title ではなく bookTitle なので、
    // findByTitleContaining ではなく findByBookTitleContaining にする
    //
    // SQLイメージ:
    // SELECT * FROM reading_records
    // WHERE book_title LIKE '%keyword%'
    //
    List<ReadingRecord> findByBookTitleContaining(
            String keyword
    );

    // =========================
    // ユーザーID + 本のタイトル検索
    // =========================
    //
    // 指定したユーザーの読書記録から、
    // 本のタイトルで部分一致検索
    //
    // SQLイメージ:
    // SELECT * FROM reading_records
    // WHERE user_id = ?
    // AND book_title LIKE '%keyword%'
    //
    List<ReadingRecord> findByUserIdAndBookTitleContaining(
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