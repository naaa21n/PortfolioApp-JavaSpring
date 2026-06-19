package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// Entity
import com.example.portfolioapi.entity.health.DiaryEntry;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// =========================
// DiaryEntry Repository
// =========================
//
// diary_entries テーブル操作用Repository
//
// DiaryEntry Entity を使って、
// diary_entries テーブルを操作する
//
// 主な機能
//
// ・全件取得
// ・ID検索
// ・保存
// ・更新
// ・削除
//
// JpaRepositoryから継承される
// 標準CRUD機能を利用する
//
// 対応テーブル:
//
// diary_entries
//
// JpaRepository<エンティティ型, ID型>
//
// DiaryEntry の主キー id は UUID 型なので、
// 第2引数は String ではなく UUID にする
//
public interface DiaryEntryRepository
        extends JpaRepository<DiaryEntry, UUID> {

    // =========================
    // 日記日付検索
    // =========================
    //
    // 指定された日付の日記を取得
    //
    // Entity側のフィールド名は date ではなく diaryDate なので、
    // findByDate ではなく findByDiaryDate にする
    //
    // SQLイメージ:
    // SELECT * FROM diary_entries
    // WHERE diary_date = ?
    //
    Optional<DiaryEntry> findByDiaryDateAndUserId(
            LocalDate diaryDate,
            UUID userId
    );

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーの日記一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM diary_entries
    // WHERE user_id = ?
    //
    List<DiaryEntry> findByUserId(
            UUID userId
    );

    // =========================
    // ユーザーID + 日記日付検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した日付の日記を取得
    //
    // 実際のログインユーザーごとの日記では
    // このメソッドをよく使う
    //
    // SQLイメージ:
    // SELECT * FROM diary_entries
    // WHERE user_id = ?
    // AND diary_date = ?
    //
    Optional<DiaryEntry> findByUserIdAndDiaryDate(
            UUID userId,
            LocalDate diaryDate
    );

    // =========================
    // ユーザーID + 期間検索
    // =========================
    //
    // 指定したユーザーの日記を
    // 指定期間で取得
    //
    // カレンダー表示、履歴表示、週間/月間表示で便利
    //
    // SQLイメージ:
    // SELECT * FROM diary_entries
    // WHERE user_id = ?
    // AND diary_date BETWEEN ? AND ?
    //
    List<DiaryEntry> findByUserIdAndDiaryDateBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
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