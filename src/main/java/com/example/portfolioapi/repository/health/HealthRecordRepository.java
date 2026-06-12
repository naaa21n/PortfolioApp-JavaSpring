package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// HealthRecord Entity
import com.example.portfolioapi.entity.health.HealthRecord;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// =========================
// HealthRecord Repository
// =========================
//
// health_records テーブル操作用Repository
//
// HealthRecord Entity を使って、
// health_records テーブルを操作する
//
// JpaRepositoryを継承することで
// 以下の機能が自動利用できる
//
// ・一覧取得
// ・1件取得
// ・登録
// ・更新
// ・削除
//
// 対応テーブル:
//
// health_records
//
public interface HealthRecordRepository
        extends JpaRepository<HealthRecord, UUID> {

    // =========================
    // 日付検索
    // =========================
    //
    // 指定日付の健康記録を取得
    //
    // Entity側のフィールド名は date ではなく recordDate なので、
    // findByDate ではなく findByRecordDate にする
    //
    // SQLイメージ:
    // SELECT * FROM health_records
    // WHERE record_date = ?
    //
    Optional<HealthRecord> findByRecordDate(
            LocalDate recordDate
    );

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーの健康記録一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM health_records
    // WHERE user_id = ?
    //
    List<HealthRecord> findByUserId(
            UUID userId
    );

    // =========================
    // ユーザーID + 日付検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した日付の健康記録を取得
    //
    // 実際のログインユーザーごとの記録では
    // このメソッドをよく使う
    //
    // SQLイメージ:
    // SELECT * FROM health_records
    // WHERE user_id = ?
    // AND record_date = ?
    //
    Optional<HealthRecord> findByUserIdAndRecordDate(
            UUID userId,
            LocalDate recordDate
    );

    // =========================
    // ユーザーID + 期間検索
    // =========================
    //
    // 指定したユーザーの健康記録を
    // 指定期間で取得
    //
    // カレンダー表示、グラフ表示、週間/月間集計で使いやすい
    //
    // SQLイメージ:
    // SELECT * FROM health_records
    // WHERE user_id = ?
    // AND record_date BETWEEN ? AND ?
    //
    List<HealthRecord> findByUserIdAndRecordDateBetween(
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