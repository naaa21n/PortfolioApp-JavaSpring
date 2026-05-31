package com.example.portfolioapi.controller.health;

import com.example.portfolioapi.entity.health.Diary;
import com.example.portfolioapi.repository.health.DiaryRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
@CrossOrigin(origins = "http://localhost:3000")
public class DiaryController {

    private final DiaryRepository diaryRepository;

    public DiaryController(
            DiaryRepository diaryRepository
    ) {
        this.diaryRepository =
                diaryRepository;
    }

    // =========================
    // 全件取得
    // GET /api/diaries
    // =========================

    @GetMapping
    public List<Diary> getDiaries() {

        return diaryRepository.findAll();
    }

    // =========================
    // 日付取得
    // GET /api/diaries/date/{date}
    // =========================

    @GetMapping("/date/{date}")
    public List<Diary> getByDate(
            @PathVariable String date
    ) {

        return diaryRepository
                .findByDate(date);
    }

    // =========================
    // 登録
    // POST /api/diaries
    // =========================

    @PostMapping
    public Diary addDiary(
            @RequestBody Diary diary
    ) {

        return diaryRepository.save(
                diary
        );
    }

    // =========================
    // 更新
    // PUT /api/diaries/{id}
    // =========================

    @PutMapping("/{id}")
    public Diary updateDiary(
            @PathVariable String id,
            @RequestBody Diary request
    ) {

        Diary diary =
                diaryRepository
                        .findById(id)
                        .orElse(null);

        if (diary == null) {
            return null;
        }

        diary.setDate(
                request.getDate());

        diary.setContent(
                request.getContent());

        return diaryRepository.save(
                diary
        );
    }

    // =========================
    // 削除
    // DELETE /api/diaries/{id}
    // =========================

    @DeleteMapping("/{id}")
    public void deleteDiary(
            @PathVariable String id
    ) {

        diaryRepository.deleteById(id);
    }
}