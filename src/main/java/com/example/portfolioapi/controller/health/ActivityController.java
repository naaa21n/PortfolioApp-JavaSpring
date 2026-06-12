package com.example.portfolioapi.controller.health;

// =========================
// Import
// =========================

import com.example.portfolioapi.entity.health.ActivityRecord;
import com.example.portfolioapi.repository.health.ActivityRecordRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// =========================
// Activity Controller
// =========================
//
// 行動記録(Activity)を管理する
// REST API Controller
//
// URL:
// GET    /api/activities
// GET    /api/activities/date/{date}
// POST   /api/activities
// DELETE /api/activities/{id}
//
@RestController

// APIの共通URL
@RequestMapping("/api/activities")

// Next.jsからアクセス可能にする
public class ActivityController {

    // =========================
    // Repository
    // =========================
    //
    // Database操作担当
    //
    private final ActivityRecordRepository activityRepository;

    // =========================
    // Constructor Injection
    // =========================
    //
    // Springが自動でRepositoryを渡してくれる
    //
    public ActivityController(
            ActivityRecordRepository activityRepository
    ) {
        this.activityRepository =
                activityRepository;
    }

    // =========================
    // 全件取得
    // =========================
    //
    // GET
    // /api/activities
    //
    // Activityテーブルの全データ取得
    //
    @GetMapping
    public List<ActivityRecord> getAll() {

        return activityRepository.findAll();
    }

    // =========================
    // 日付検索
    // =========================
    //
    // GET
    // /api/activities/date/2026-05-31
    //
    // 指定日付の行動記録を取得
    //
    @GetMapping("/date/{date}")
    public ActivityRecord getByDate(
            @PathVariable String date
    ) {

        return activityRepository
                .findByDate(date)
                .orElse(null);
    }

    // =========================
    // 登録
    // =========================
    //
    // POST
    // /api/activities
    //
    // JSONを受け取ってDB保存
    //
    @PostMapping
    public ActivityRecord add(
            @RequestBody ActivityRecord activity
    ) {

        // =====================
        // デバッグ確認
        // =====================

        System.out.println(
                "id=" + activity.getId()
        );

        System.out.println(
                "date=" + activity.getDate()
        );

        System.out.println(
                "sleep=" + activity.getSleep()
        );

        System.out.println(
                "work=" + activity.getWork()
        );

        System.out.println(
                "study=" + activity.getStudy()
        );

        System.out.println(
                "exercise=" + activity.getExercise()
        );

        System.out.println(
                "hobby=" + activity.getHobby()
        );

        System.out.println(
                "other=" + activity.getOther()
        );

        // =====================
        // DB保存
        // =====================

        return activityRepository.save(
                activity
        );
    }

    // =========================
    // 削除
    // =========================
    //
    // DELETE
    // /api/activities/{id}
    //
    // ID指定で削除
    //
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ) {

        activityRepository.deleteById(id);
    }
}