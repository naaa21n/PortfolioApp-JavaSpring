package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// JournalEntry Entity
import com.example.portfolioapi.entity.health.JournalEntry;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// =========================
// JournalEntry Repository
// =========================
//
// journal_entries テーブル操作用Repository
//
// JournalEntry Entity を使って、
// journal_entries テーブルを操作する
//
// Spring Data JPAによって
// 基本CRUD機能を自動提供する
//
// JpaRepository<エンティティ型, ID型>
//
// JournalEntry の主キー id は UUID 型なので、
// 第2引数は String ではなく UUID にする
//
// 対応テーブル:
//
// journal_entries
//
public interface JournalEntryRepository
        extends JpaRepository<JournalEntry, UUID> {

    // =========================
    // ジャーナル日付検索
    // =========================
    //
    // 指定した日付のジャーナリング記録を取得
    //
    // Entity側のフィールド名は journalDate なので、
    // findByDate ではなく findByJournalDate にする
    //
    // SQLイメージ:
    // SELECT * FROM journal_entries
    // WHERE journal_date = ?
    //
    Optional<JournalEntry> findByJournalDate(
            LocalDate journalDate
    );

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーの
    // ジャーナリング記録一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM journal_entries
    // WHERE user_id = ?
    //
    List<JournalEntry> findByUserId(
            UUID userId
    );

    // =========================
    // ユーザーID + ジャーナル日付検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した日付のジャーナリング記録を取得
    //
    // 実際のログインユーザーごとの記録では
    // このメソッドをよく使う
    //
    // SQLイメージ:
    // SELECT * FROM journal_entries
    // WHERE user_id = ?
    // AND journal_date = ?
    //
    Optional<JournalEntry> findByUserIdAndJournalDate(
            UUID userId,
            LocalDate journalDate
    );

    // =========================
    // ユーザーID + 期間検索
    // =========================
    //
    // 指定したユーザーの
    // 指定期間内のジャーナリング記録を取得
    //
    // カレンダー表示、履歴表示、週間/月間表示で便利
    //
    // SQLイメージ:
    // SELECT * FROM journal_entries
    // WHERE user_id = ?
    // AND journal_date BETWEEN ? AND ?
    //
    List<JournalEntry> findByUserIdAndJournalDateBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );

    // =========================
    // JpaRepositoryで自動利用可能
    // =========================

    // save()
    // -> 登録 / 更新
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