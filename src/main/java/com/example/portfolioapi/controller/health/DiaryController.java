package com.example.portfolioapi.controller.health;

// =========================
// Import
// =========================

import com.example.portfolioapi.entity.health.Diary;
import com.example.portfolioapi.repository.health.DiaryRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// =========================
// Diary Controller
// =========================
//
// 日記(Diary)を管理する
// REST API Controller
//
// URL
// GET    /api/diaries
// GET    /api/diaries/date/{date}
// POST   /api/diaries
// PUT    /api/diaries/{id}
// DELETE /api/diaries/{id}
//
@RestController

// API共通URL
@RequestMapping("/api/diaries")

// Next.jsからアクセス許可
@CrossOrigin(origins = "http://localhost:3000")
public class DiaryController {

    // =========================
    // Repository
    // =========================
    //
    // Diaryテーブル操作担当
    //
    private final DiaryRepository diaryRepository;

    // =========================
    // Constructor Injection
    // =========================
    //
    // Springが自動でRepositoryを注入
    //
    public DiaryController(
            DiaryRepository diaryRepository
    ) {
        this.diaryRepository =
                diaryRepository;
    }

    // =========================
    // 全件取得
    // =========================
    //
    // GET
    // /api/diaries
    //
    // 日記一覧取得
    //
    @GetMapping
    public List<Diary> getDiaries() {

        return diaryRepository.findAll();
    }

    // =========================
    // 日付検索
    // =========================
    //
    // GET
    // /api/diaries/date/{date}
    //
    // 指定日付の日記取得
    //
    @GetMapping("/date/{date}")
    public List<Diary> getByDate(
            @PathVariable String date
    ) {

        return diaryRepository
                .findByDate(date);
    }

    // =========================
    // 登録
    // =========================
    //
    // POST
    // /api/diaries
    //
    // JSONを受け取って保存
    //
    @PostMapping
    public Diary addDiary(
            @RequestBody Diary diary
    ) {

        // =====================
        // デバッグ確認
        // =====================

        System.out.println(
                "id=" + diary.getId()
        );

        System.out.println(
                "date=" + diary.getDate()
        );

        System.out.println(
                "content=" + diary.getContent()
        );

        // =====================
        // DB保存
        // =====================

        return diaryRepository.save(
                diary
        );
    }

    // =========================
    // 更新
    // =========================
    //
    // PUT
    // /api/diaries/{id}
    //
    // 指定IDの日記を更新
    //
    @PutMapping("/{id}")
    public Diary updateDiary(
            @PathVariable String id,
            @RequestBody Diary request
    ) {

        // 既存データ取得
        Diary diary =
                diaryRepository
                        .findById(id)
                        .orElse(null);

        // データが存在しない場合
        if (diary == null) {
            return null;
        }

        // 更新内容反映
        diary.setDate(
                request.getDate());

        diary.setContent(
                request.getContent());

        // 更新保存
        return diaryRepository.save(
                diary
        );
    }

    // =========================
    // 削除
    // =========================
    //
    // DELETE
    // /api/diaries/{id}
    //
    // 指定IDの日記削除
    //
    @DeleteMapping("/{id}")
    public void deleteDiary(
            @PathVariable String id
    ) {

        diaryRepository.deleteById(id);
    }
}