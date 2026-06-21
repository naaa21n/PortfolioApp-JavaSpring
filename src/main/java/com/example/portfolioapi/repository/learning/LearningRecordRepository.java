package com.example.portfolioapi.repository.learning;

// =========================
// Import
// =========================

// LearningRecord Entity
import com.example.portfolioapi.entity.learning.LearningRecord;

// Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Java
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

// =========================
// LearningRecord Repository
// =========================
//
// learning_records テーブル操作用Repository
//
// LearningRecord Entity を使って、
// learning_records テーブルを操作する
//
// DB操作を担当する
//
// JpaRepository<エンティティ型, ID型>
//
// LearningRecord の主キー id は UUID 型なので、
// 第2引数は String ではなく UUID にする
//
public interface LearningRecordRepository
        extends JpaRepository<LearningRecord, UUID> {

    // =========================
    // ユーザーID検索
    // =========================
    //
    // 指定したユーザーの学習記録一覧を取得
    //
    // SQLイメージ:
    // SELECT * FROM learning_records
    // WHERE user_id = ?
    //
    List<LearningRecord> findByUserId(
            UUID userId
    );

    // =========================
    // 学習日検索
    // =========================
    //
    // 指定した学習日の学習記録を取得
    //
    // Entity側のフィールド名は studyDate ではなく studiedOn なので、
    // findByStudyDate ではなく findByStudiedOn にする
    //
    // SQLイメージ:
    // SELECT * FROM learning_records
    // WHERE studied_on = ?
    //
    List<LearningRecord> findByStudiedOn(
            LocalDate studiedOn
    );

    // =========================
    // ユーザーID + 学習日検索
    // =========================
    //
    // 指定したユーザーの、
    // 指定した学習日の学習記録を取得
    //
    // 1日に複数の学習記録がありえるため、
    // Optional ではなく List にする
    //
    // SQLイメージ:
    // SELECT * FROM learning_records
    // WHERE user_id = ?
    // AND studied_on = ?
    //
    List<LearningRecord> findByUserIdAndStudiedOn(
            UUID userId,
            LocalDate studiedOn
    );

    // =========================
    // ユーザーID + 期間検索
    // =========================
    //
    // 指定したユーザーの学習記録を
    // 指定期間で取得
    //
    // 週間グラフ、月間グラフ、カレンダー表示で便利
    //
    // SQLイメージ:
    // SELECT * FROM learning_records
    // WHERE user_id = ?
    // AND studied_on BETWEEN ? AND ?
    //
    List<LearningRecord> findByUserIdAndStudiedOnBetween(
            UUID userId,
            LocalDate startDate,
            LocalDate endDate
    );

    // =========================
    // タイトル検索
    // =========================
    //
    // 学習タイトルで部分一致検索
    //
    // 例:
    // "Spring" で検索すると
    // "Spring Boot"
    // "Spring Security"
    // などを取得できる
    //
    // SQLイメージ:
    // SELECT * FROM learning_records
    // WHERE title LIKE '%keyword%'
    //
    List<LearningRecord> findByTitleContaining(
            String keyword
    );

    // =========================
    // ユーザーID + タイトル検索
    // =========================
    //
    // 指定したユーザーの学習記録から、
    // タイトル部分一致で検索
    //
    // SQLイメージ:
    // SELECT * FROM learning_records
    // WHERE user_id = ?
    // AND title LIKE '%keyword%'
    //
    List<LearningRecord> findByUserIdAndTitleContaining(
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