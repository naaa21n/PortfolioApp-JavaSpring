package com.example.portfolioapi.controller;

import com.example.portfolioapi.entity.Health;
import com.example.portfolioapi.repository.HealthRepository;

import org.springframework.web.bind.annotation.*;

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

        return healthRepository.save(health);
    }

    // =========================
    // 更新
    // PUT /api/healths/{id}
    // =========================
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

        health.setDate(
                request.getDate());

        health.setSteps(
                request.getSteps());

        health.setExerciseMinutes(
                request.getExerciseMinutes());

        health.setSleepHours(
                request.getSleepHours());

        health.setWaterMl(
                request.getWaterMl());

        health.setDiary(
                request.getDiary());

        health.setGratitude(
                request.getGratitude());

        health.setAchievement(
                request.getAchievement());

        health.setTomorrowGoal(
                request.getTomorrowGoal());

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