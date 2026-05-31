package com.example.portfolioapi.controller.health;

import com.example.portfolioapi.entity.health.Health;
import com.example.portfolioapi.repository.health.HealthRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/healths")
@CrossOrigin(origins = "http://localhost:3000")



public class HealthController {

    private final HealthRepository healthRepository;

    public HealthController(
            HealthRepository healthRepository
    ) {
        this.healthRepository = healthRepository;
    }

    // =========================
    // 一覧取得
    // GET /api/healths
    // =========================
    @GetMapping
    public List<Health> getHealths() {

        return healthRepository.findAll();
    }

    // =========================
    // 月データ取得
    // GET /api/healths/month
    // =========================

    @GetMapping("/month")
    public List<Health> getMonthHealths() {

        return healthRepository.findAll();
    }


    // =========================
    // 日付で取得
    // GET /api/healths/date/{date}
    // =========================

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
    // GET /api/healths/{id}
    // =========================
    @GetMapping("/{id}")
    public Health getHealth(
            @PathVariable String id
    ) {

        return healthRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // 新規追加
    // POST /api/healths
    // =========================
    @PostMapping
    public Health addHealth(
            @RequestBody Health health
    ) {

        System.out.println(health.getDate());
        System.out.println(health.getSteps());

        return healthRepository.save(health);
    }

    // =========================
    // 更新
    // PUT /api/healths/{id}
    // =========================
    // 更新
    @PutMapping("/{id}")
    public Health updateHealth(
            @PathVariable String id,
            @RequestBody Health request
    ) {

        Optional<Health> optional =
                healthRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Health health = optional.get();

        health.setSteps(
                request.getSteps());

        health.setExerciseMinutes(
                request.getExerciseMinutes());

        health.setSleepHours(
                request.getSleepHours());

        health.setWaterMl(
                request.getWaterMl());

        return healthRepository.save(
                health
        );
    }

    // =========================
    // 削除
    // DELETE /api/healths/{id}
    // =========================
    @DeleteMapping("/{id}")
    public void deleteHealth(
            @PathVariable String id
    ) {

        healthRepository.deleteById(id);
    }
}