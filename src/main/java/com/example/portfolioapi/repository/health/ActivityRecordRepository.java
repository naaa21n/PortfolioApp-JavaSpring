package com.example.portfolioapi.repository.health;

// =========================
// Import
// =========================

// Entity
import com.example.portfolioapi.entity.health.ActivityRecord;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// =========================
// ActivityRecord Repository
// =========================
//
// activity_records テーブル操作用Repository
//
// ActivityRecord Entity を使って、
// activity_records テーブルを操作する
//
// JpaRepository<エンティティ型, ID型>
//
// ActivityRecord の主キー id は UUID 型なので、
// 第2引数は UUID にする
//
public interface ActivityRecordRepository
        extends JpaRepository<ActivityRecord, UUID> {

    // =========================
    // 日付検索
    // =========================
    //
    // 指定された日付のActivityRecordを取得
    //
    // Entity側のフィールド名が recordDate なので、
    // findByDate ではなく findByRecordDate にする
    //
    // SQLイメージ:
    // SELECT * FROM activity_records
    // WHERE record_date = ?
    //
    Optional<ActivityRecord> findByRecordDate(
            LocalDate recordDate
    );

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーの活動記録一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM activity_records
    // WHERE user_id = ?
    //
    List<ActivityRecord> findByUserId(
            UUID userId
    );

    // =========================
    // ユーザーID + 日付検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した日付の活動記録を取得
    //
    // 実際のログインユーザーごとの記録では
    // このメソッドをよく使う
    //
    // SQLイメージ:
    // SELECT * FROM activity_records
    // WHERE user_id = ?
    // AND record_date = ?
    //
    Optional<ActivityRecord> findByUserIdAndRecordDate(
            UUID userId,
            LocalDate recordDate
    );

    // =========================
    // ユーザーID + 期間検索
    // =========================
    //
    // 指定したユーザーの活動記録を
    // 指定期間で取得
    //
    // グラフ表示やカレンダー表示で便利
    //
    // SQLイメージ:
    // SELECT * FROM activity_records
    // WHERE user_id = ?
    // AND record_date BETWEEN ? AND ?
    //
    List<ActivityRecord> findByUserIdAndRecordDateBetween(
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