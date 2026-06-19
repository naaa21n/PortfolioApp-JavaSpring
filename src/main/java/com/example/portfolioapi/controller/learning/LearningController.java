package com.example.portfolioapi.controller.learning;

// =========================
// Entity Import
// =========================

// 学習記録Entity
import com.example.portfolioapi.entity.learning.LearningRecord;

// 読書記録Entity
import com.example.portfolioapi.entity.learning.ReadingRecord;

// =========================
// Repository Import
// =========================

// 学習記録Repository
import com.example.portfolioapi.repository.learning.LearningRecordRepository;

// 読書記録Repository
import com.example.portfolioapi.repository.learning.ReadingRecordRepository;

// =========================
// Spring MVC Import
// =========================

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

// =========================
// Java Import
// =========================

import java.util.List;
import java.util.UUID;

// =========================
// Learning Controller
// =========================
//
// 学習ページで使用するAPI Controller
//
// このControllerでは以下の2種類のデータを扱う
//
// ・learning_records
// ・reading_records
//
// つまり、学習ページで使う
// 「学習記録」と「読書記録」をまとめて管理する
//
// URL共通部分:
//
// /api/learning
//
@RestController
@RequestMapping("/api/learning")

// Next.jsが別ポートで動く場合に必要になることがある
// 例:
// @CrossOrigin(origins = "http://localhost:3000")
public class LearningController {

    // =========================
    // Repository
    // =========================

    // 学習記録テーブル操作用Repository
    private final LearningRecordRepository learningRecordRepository;

    // 読書記録テーブル操作用Repository
    private final ReadingRecordRepository readingRecordRepository;

    // =========================
    // Constructor Injection
    // =========================
    //
    // Springが自動でRepositoryを注入する
    //
    public LearningController(
            LearningRecordRepository learningRecordRepository,
            ReadingRecordRepository readingRecordRepository
    ) {
        this.learningRecordRepository = learningRecordRepository;
        this.readingRecordRepository = readingRecordRepository;
    }

    // ============================================================
    // LearningRecord API
    // ============================================================

    // =========================
    // 学習記録一覧取得
    // =========================
    //
    // GET:
    // /api/learning/records
    //
    @GetMapping("/records")
    public List<LearningRecord> getLearningRecords(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // userIdで絞り込み表示する
        // WHERE userId = ? 指定と同じ
        return learningRecordRepository.findByUserId(userId);
    }

    // =========================
    // 学習記録追加
    // =========================
    //
    // POST:
    // /api/learning/records
    //
    @PostMapping("/records")
    public List<LearningRecord> addLearningRecord(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody LearningRecord learningRecord
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());
        learningRecord.setUserId(userId);

        // JSONデータをDB保存
        //
        // SQLイメージ:
        // INSERT INTO learning_records ...
        learningRecordRepository.save(learningRecord);

        // 保存後の一覧返却
        return learningRecordRepository.findByUserId(userId);
    }

    // =========================
    // 学習記録削除
    // =========================
    //
    // DELETE:
    // /api/learning/records/{id}
    //
    // 例:
    // /api/learning/records/550e8400-e29b-41d4-a716-446655440000
    //
    @DeleteMapping("/records/{id}")
    public List<LearningRecord> deleteLearningRecord(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // 指定IDの学習記録を削除
        //
        // SQLイメージ:
        // DELETE FROM learning_records
        // WHERE id = ?
        learningRecordRepository.deleteById(id);

        // 削除後の一覧返却
        return learningRecordRepository.findByUserId(userId);
    }

    // ============================================================
    // ReadingRecord API
    // ============================================================

    // =========================
    // 読書記録一覧取得
    // =========================
    //
    // GET:
    // /api/learning/readings
    //
    @GetMapping("/readings")
    public List<ReadingRecord> getReadingRecords(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // userIdで絞り込み表示する
        // WHERE userId = ? 指定と同じ
        return readingRecordRepository.findByUserId(userId);
    }

    // =========================
    // 読書記録追加
    // =========================
    //
    // POST:
    // /api/learning/readings
    //
    @PostMapping("/readings")
    public List<ReadingRecord> addReadingRecord(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody ReadingRecord readingRecord
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());
        readingRecord.setUserId(userId);

        // JSONデータをDB保存
        //
        // SQLイメージ:
        // INSERT INTO reading_records ...
        readingRecordRepository.save(readingRecord);

        // 保存後の一覧返却
        return readingRecordRepository.findByUserId(userId);
    }

    // =========================
    // 読書記録削除
    // =========================
    //
    // DELETE:
    // /api/learning/readings/{id}
    //
    // 例:
    // /api/learning/readings/550e8400-e29b-41d4-a716-446655440000
    //
    @DeleteMapping("/readings/{id}")
    public List<ReadingRecord> deleteReadingRecord(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // 指定IDの読書記録を削除
        //
        // SQLイメージ:
        // DELETE FROM reading_records
        // WHERE id = ?
        readingRecordRepository.deleteById(id);

        // 削除後の一覧返却
        return readingRecordRepository.findByUserId(userId);
    }
}