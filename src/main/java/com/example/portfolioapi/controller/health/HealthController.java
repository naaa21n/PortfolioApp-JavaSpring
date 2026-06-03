package com.example.portfolioapi.controller.health;

// =========================
// Import
// =========================

import com.example.portfolioapi.entity.health.Health;
import com.example.portfolioapi.repository.health.HealthRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// =========================
// Health Controller
// =========================
//
// 健康データを管理する
// REST API Controller
//
// URL
// GET    /api/healths
// GET    /api/healths/month
// GET    /api/healths/date/{date}
// GET    /api/healths/{id}
// POST   /api/healths
// PUT    /api/healths/{id}
// DELETE /api/healths/{id}
//
@RestController

// API共通URL
@RequestMapping("/api/healths")

// Next.jsからアクセス許可
@CrossOrigin(origins = "http://localhost:3000")
public class HealthController {

    // =========================
    // Repository
    // =========================
    //
    // Healthテーブル操作担当
    //
    private final HealthRepository healthRepository;

    // =========================
    // Constructor Injection
    // =========================
    //
    // SpringがRepositoryを自動注入
    //
    public HealthController(
            HealthRepository healthRepository
    ) {
        this.healthRepository =
                healthRepository;
    }

    // =========================
    // 全件取得
    // =========================
    //
    // GET
    // /api/healths
    //
    // 健康データ一覧取得
    //
    @GetMapping
    public List<Health> getHealths() {

        return healthRepository.findAll();
    }

    // =========================
    // 月データ取得
    // =========================
    //
    // GET
    // /api/healths/month
    //
    // 月別データ取得
    //
    @GetMapping("/month")
    public List<Health> getMonthHealths() {

        return healthRepository.findAll();
    }

    // =========================
    // 日付検索
    // =========================
    //
    // GET
    // /api/healths/date/{date}
    //
    // 指定日付の健康データ取得
    //
    @GetMapping("/date/{date}")
    public Health getByDate(
            @PathVariable String date
    ) {

        return healthRepository
                .findByDate(
                        LocalDate.parse(date)
                )
                .orElse(null);
    }

    // =========================
    // 1件取得
    // =========================
    //
    // GET
    // /api/healths/{id}
    //
    // IDで健康データ取得
    //
    @GetMapping("/{id}")
    public Health getHealth(
            @PathVariable String id
    ) {

        return healthRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // 新規登録
    // =========================
    //
    // POST
    // /api/healths
    //
    // 健康データ保存
    //
    @PostMapping
    public Health addHealth(
            @RequestBody Health health
    ) {

        // =====================
        // デバッグ確認
        // =====================

        System.out.println(
                "date=" + health.getDate()
        );

        System.out.println(
                "steps=" + health.getSteps()
        );

        System.out.println(
                "exerciseMinutes="
                        + health.getExerciseMinutes()
        );

        System.out.println(
                "sleepHours="
                        + health.getSleepHours()
        );

        System.out.println(
                "waterMl="
                        + health.getWaterMl()
        );

        // =====================
        // DB保存
        // =====================

        return healthRepository.save(
                health
        );
    }

    // =========================
    // 更新
    // =========================
    //
    // PUT
    // /api/healths/{id}
    //
    // 健康データ更新
    //
    @PutMapping("/{id}")
    public Health updateHealth(
            @PathVariable String id,
            @RequestBody Health request
    ) {

        Optional<Health> optional =
                healthRepository.findById(id);

        // データなし
        if (optional.isEmpty()) {
            return null;
        }

        Health health =
                optional.get();

        // 更新内容反映

        health.setSteps(
                request.getSteps());

        health.setExerciseMinutes(
                request.getExerciseMinutes());

        health.setSleepHours(
                request.getSleepHours());

        health.setWaterMl(
                request.getWaterMl());

        // 更新保存

        return healthRepository.save(
                health
        );
    }

    // =========================
    // 削除
    // =========================
    //
    // DELETE
    // /api/healths/{id}
    //
    // 指定IDの健康データ削除
    //
    @DeleteMapping("/{id}")
    public void deleteHealth(
            @PathVariable String id
    ) {

        healthRepository.deleteById(id);
    }
}