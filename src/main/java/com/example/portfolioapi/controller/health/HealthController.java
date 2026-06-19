package com.example.portfolioapi.controller.health;

// =========================
// Entity Import
// =========================

import com.example.portfolioapi.entity.health.HealthRecord;
import com.example.portfolioapi.entity.health.ActivityRecord;
import com.example.portfolioapi.entity.health.DiaryEntry;
import com.example.portfolioapi.entity.health.JournalEntry;

// =========================
// Repository Import
// =========================

import com.example.portfolioapi.repository.health.HealthRecordRepository;
import com.example.portfolioapi.repository.health.ActivityRecordRepository;
import com.example.portfolioapi.repository.health.DiaryEntryRepository;
import com.example.portfolioapi.repository.health.JournalEntryRepository;

// =========================
// Spring MVC Import
// =========================

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

// =========================
// Java Import
// =========================

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// =========================
// Health Controller
// =========================
//
// 健康ページで使用するAPI Controller
//
// このControllerでは以下の4種類のデータを扱う
//
// ・health_records
// ・activity_records
// ・diary_entries
// ・journal_entries
//
// URL共通部分:
//
// /api/health
//
@RestController
@RequestMapping("/api/health")

// Next.jsが別ポートで動く場合に必要になることがある
// 例:
// @CrossOrigin(origins = "http://localhost:3000")
public class HealthController {

    // =========================
    // Repository
    // =========================

    // 健康記録テーブル操作用Repository
    private final HealthRecordRepository healthRecordRepository;

    // 活動記録テーブル操作用Repository
    private final ActivityRecordRepository activityRecordRepository;

    // 日記テーブル操作用Repository
    private final DiaryEntryRepository diaryEntryRepository;

    // ジャーナリングテーブル操作用Repository
    private final JournalEntryRepository journalEntryRepository;

    // =========================
    // Constructor Injection
    // =========================
    //
    // Springが自動でRepositoryを注入する
    //
    public HealthController(
            HealthRecordRepository healthRecordRepository,
            ActivityRecordRepository activityRecordRepository,
            DiaryEntryRepository diaryEntryRepository,
            JournalEntryRepository journalEntryRepository
    ) {
        this.healthRecordRepository = healthRecordRepository;
        this.activityRecordRepository = activityRecordRepository;
        this.diaryEntryRepository = diaryEntryRepository;
        this.journalEntryRepository = journalEntryRepository;
    }

    // ============================================================
    // HealthRecord API
    // ============================================================

    // =========================
    // 健康記録一覧取得
    // =========================
    //
    // GET:
    // /api/health/records
    //
    @GetMapping("/records")
    public List<HealthRecord> getHealthRecords(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // userIdで絞り込み表示する
        // WHERE userId = ? 指定と同じ
        return healthRecordRepository.findByUserId(userId);
    }

    // =========================
    // 健康記録 日付検索
    // =========================
    //
    // GET:
    // /api/health/records/date/2026-06-13
    //
    @GetMapping("/records/date/{date}")
    public HealthRecord getHealthRecordByDate(
            @PathVariable String date
    ) {
        return healthRecordRepository
                .findByRecordDate(LocalDate.parse(date))
                .orElse(null);
    }

    // =========================
    // 健康記録 1件取得
    // =========================
    //
    // GET:
    // /api/health/records/{id}
    //
    @GetMapping("/records/{id}")
    public HealthRecord getHealthRecord(
            @PathVariable UUID id
    ) {
        return healthRecordRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // 健康記録 新規登録
    // =========================
    //
    // POST:
    // /api/health/records
    //
    @PostMapping("/records")
    public HealthRecord addHealthRecord(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody HealthRecord healthRecord
    ) {
        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());
        healthRecord.setUserId(userId);

        // テーブルへ保存処理
        return healthRecordRepository.save(healthRecord);

        //healthRecordRepository.findByUserId(userId);
    }

    // =========================
    // 健康記録 更新
    // =========================
    //
    // PUT:
    // /api/health/records/{id}
    //
    @PutMapping("/records/{id}")
    public HealthRecord updateHealthRecord(
            @PathVariable UUID id,
            @RequestBody HealthRecord request
    ) {
        Optional<HealthRecord> optional =
                healthRecordRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        HealthRecord healthRecord = optional.get();

        healthRecord.setRecordDate(request.getRecordDate());
        healthRecord.setSteps(request.getSteps());
        healthRecord.setExerciseMinutes(request.getExerciseMinutes());
        healthRecord.setSleepHours(request.getSleepHours());
        healthRecord.setWaterMl(request.getWaterMl());

        return healthRecordRepository.save(healthRecord);
    }

    // =========================
    // 健康記録 削除
    // =========================
    //
    // DELETE:
    // /api/health/records/{id}
    //
    @DeleteMapping("/records/{id}")
    public void deleteHealthRecord(
            @PathVariable UUID id
    ) {
        healthRecordRepository.deleteById(id);
    }

    // ============================================================
    // ActivityRecord API
    // ============================================================

    // =========================
    // 活動記録一覧取得
    // =========================
    //
    // GET:
    // /api/health/activities
    //
    @GetMapping("/activities")
    public List<ActivityRecord> getActivityRecords(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // userIdで絞り込み表示する
        // WHERE userId = ? 指定と同じ
        return activityRecordRepository.findByUserId(userId);
    }

    // =========================
    // 活動記録 日付検索
    // =========================
    //
    // GET:
    // /api/health/activities/date/2026-06-13
    //
    @GetMapping("/activities/date/{date}")
    public ActivityRecord getActivityRecordByDate(
            @PathVariable String date
    ) {
        return activityRecordRepository
                .findByRecordDate(LocalDate.parse(date))
                .orElse(null);
    }

    // =========================
    // 活動記録 1件取得
    // =========================
    //
    // GET:
    // /api/health/activities/{id}
    //
    @GetMapping("/activities/{id}")
    public ActivityRecord getActivityRecord(
            @PathVariable UUID id
    ) {
        return activityRecordRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // 活動記録 新規登録
    // =========================
    //
    // POST:
    // /api/health/activities
    //
    @PostMapping("/activities")
    public ActivityRecord addActivityRecord(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody ActivityRecord activityRecord
    ) {
        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());
        activityRecord.setUserId(userId);

        // テーブルへ保存処理
        return activityRecordRepository.save(activityRecord);
    }

    // =========================
    // 活動記録 更新
    // =========================
    //
    // PUT:
    // /api/health/activities/{id}
    //
    @PutMapping("/activities/{id}")
    public ActivityRecord updateActivityRecord(
            @PathVariable UUID id,
            @RequestBody ActivityRecord request
    ) {
        Optional<ActivityRecord> optional =
                activityRecordRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        ActivityRecord activityRecord = optional.get();

        activityRecord.setRecordDate(request.getRecordDate());
        activityRecord.setSleep(request.getSleep());
        activityRecord.setWork(request.getWork());
        activityRecord.setStudy(request.getStudy());
        activityRecord.setExercise(request.getExercise());
        activityRecord.setHobby(request.getHobby());
        activityRecord.setOther(request.getOther());

        return activityRecordRepository.save(activityRecord);
    }

    // =========================
    // 活動記録 削除
    // =========================
    //
    // DELETE:
    // /api/health/activities/{id}
    //
    @DeleteMapping("/activities/{id}")
    public void deleteActivityRecord(
            @PathVariable UUID id
    ) {
        activityRecordRepository.deleteById(id);
    }

    // ============================================================
    // DiaryEntry API
    // ============================================================

    // =========================
    // 日記一覧取得
    // =========================
    //
    // GET:
    // /api/health/diaries
    //
    @GetMapping("/diaries")
    public List<DiaryEntry> getDiaryEntries(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // userIdで絞り込み表示する
        // WHERE userId = ? 指定と同じ
        return diaryEntryRepository.findByUserId(userId);
    }

    // =========================
    // 日記 日付検索
    // =========================
    //
    // GET:
    // /api/health/diaries/date/2026-06-13
    //
    @GetMapping("/diaries/date/{date}")
    public DiaryEntry getDiaryEntryByDate(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String date
    ) {
        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // WHERE diary_date = ? AND user_id = ?
        // 該当情報が無ければNULLを返却
        return diaryEntryRepository
                .findByDiaryDateAndUserId(LocalDate.parse(date),userId)
                .orElse(null);
    }

    // =========================
    // 日記 1件取得
    // =========================
    //
    // GET:
    // /api/health/diaries/{id}
    //
    @GetMapping("/diaries/{id}")
    public DiaryEntry getDiaryEntry(
            @PathVariable UUID id
    ) {
        return diaryEntryRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // 日記 新規登録
    // =========================
    //
    // POST:
    // /api/health/diaries
    //
    @PostMapping("/diaries")
    public DiaryEntry addDiaryEntry(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody DiaryEntry diaryEntry
    ) {
        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());
        diaryEntry.setUserId(userId);

        // テーブルへ保存処理
        return diaryEntryRepository.save(diaryEntry);
    }

    // =========================
    // 日記 更新
    // =========================
    //
    // PUT:
    // /api/health/diaries/{id}
    //
    @PutMapping("/diaries/{id}")
    public DiaryEntry updateDiaryEntry(
            @PathVariable UUID id,
            @RequestBody DiaryEntry request
    ) {
        Optional<DiaryEntry> optional =
                diaryEntryRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        DiaryEntry diaryEntry = optional.get();

        diaryEntry.setDiaryDate(request.getDiaryDate());
        diaryEntry.setContent(request.getContent());

        return diaryEntryRepository.save(diaryEntry);
    }

    // =========================
    // 日記 削除
    // =========================
    //
    // DELETE:
    // /api/health/diaries/{id}
    //
    @DeleteMapping("/diaries/{id}")
    public void deleteDiaryEntry(
            //@AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID id
    ) {
        // JWTからuser_idを取得しbodyのuseIdに設定する
        // UUID userId = UUID.fromString(jwt.getSubject());

        diaryEntryRepository.deleteById(id);
    }

    // ============================================================
    // JournalEntry API
    // ============================================================

    // =========================
    // ジャーナル一覧取得
    // =========================
    //
    // GET:
    // /api/health/journals
    //
    @GetMapping("/journals")
    public List<JournalEntry> getJournalEntries(
            @AuthenticationPrincipal Jwt jwt
    ) {

        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());

        // userIdで絞り込み表示する
        // WHERE userId = ? 指定と同じ
        return journalEntryRepository.findByUserId(userId);
    }

    // =========================
    // ジャーナル 日付検索
    // =========================
    //
    // GET:
    // /api/health/journals/date/2026-06-13
    //
    @GetMapping("/journals/date/{date}")
    public JournalEntry getJournalEntryByDate(
            @PathVariable String date
    ) {
        return journalEntryRepository
                .findByJournalDate(LocalDate.parse(date))
                .orElse(null);
    }

    // =========================
    // ジャーナル 1件取得
    // =========================
    //
    // GET:
    // /api/health/journals/{id}
    //
    @GetMapping("/journals/{id}")
    public JournalEntry getJournalEntry(
            @PathVariable UUID id
    ) {
        return journalEntryRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // ジャーナル 新規登録
    // =========================
    //
    // POST:
    // /api/health/journals
    //
    @PostMapping("/journals")
    public JournalEntry addJournalEntry(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody JournalEntry journalEntry
    ) {
        // JWTからuser_idを取得しbodyのuseIdに設定する
        UUID userId = UUID.fromString(jwt.getSubject());
        journalEntry.setUserId(userId);

        // テーブルへ保存処理
        return journalEntryRepository.save(journalEntry);
    }

    // =========================
    // ジャーナル 更新
    // =========================
    //
    // PUT:
    // /api/health/journals/{id}
    //
    @PutMapping("/journals/{id}")
    public JournalEntry updateJournalEntry(
            @PathVariable UUID id,
            @RequestBody JournalEntry request
    ) {
        Optional<JournalEntry> optional =
                journalEntryRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        JournalEntry journalEntry = optional.get();

        journalEntry.setJournalDate(request.getJournalDate());
        journalEntry.setGratitude(request.getGratitude());
        journalEntry.setAchievement(request.getAchievement());
        journalEntry.setTomorrowGoal(request.getTomorrowGoal());
        journalEntry.setFreeText(request.getFreeText());

        return journalEntryRepository.save(journalEntry);
    }

    // =========================
    // ジャーナル 削除
    // =========================
    //
    // DELETE:
    // /api/health/journals/{id}
    //
    @DeleteMapping("/journals/{id}")
    public void deleteJournalEntry(
            @PathVariable UUID id
    ) {
        journalEntryRepository.deleteById(id);
    }
}