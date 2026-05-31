package com.example.portfolioapi.controller.health;

// =========================
// Import
// =========================

// Entity
import com.example.portfolioapi.entity.health.Journal;

// Repository
import com.example.portfolioapi.repository.health.JournalRepository;

// Spring MVC
import org.springframework.web.bind.annotation.*;

// List
import java.util.List;

// =========================
// Journal Controller
// =========================
//
// ジャーナリングAPI
//
// 主な機能
//
// ・ジャーナル一覧取得
// ・ジャーナル追加
//
// 管理データ例
//
// ・感謝したこと
// ・頑張ったこと
// ・明日の目標
// ・自由記述
//
// フロント側では
// 「ジャーナリング」エリアの
// データとして利用する
//
@RestController
@RequestMapping("/api/journals")
@CrossOrigin(origins = "http://localhost:3000")
public class JournalController {

    // =========================
    // Repository
    // =========================

    private final JournalRepository journalRepository;

    // =========================
    // Constructor Injection
    // =========================

    public JournalController(
            JournalRepository journalRepository
    ) {

        this.journalRepository =
                journalRepository;
    }

    // =========================
    // ジャーナル一覧取得
    // GET /api/journals
    // =========================
    //
    // 登録済みジャーナルを
    // 全件取得する
    //
    // 返却例
    //
    // [
    //   {
    //     "id":"1",
    //     "date":"2025-06-20",
    //     "gratitude":"家族に感謝した",
    //     "achievement":"筋トレした",
    //     "tomorrowGoal":"読書30分"
    //   }
    // ]
    //
    @GetMapping
    public List<Journal> getJournals() {

        return journalRepository.findAll();
    }

    // =========================
    // ジャーナル追加
    // POST /api/journals
    // =========================
    //
    // フロントから送られた
    // ジャーナルデータを保存する
    //
    // 送信例
    //
    // {
    //   "id":"1",
    //   "date":"2025-06-20",
    //   "gratitude":"家族に感謝した",
    //   "achievement":"筋トレした",
    //   "tomorrowGoal":"読書30分",
    //   "freeMemo":"今日は良い一日だった"
    // }
    //
    @PostMapping
    public Journal addJournal(
            @RequestBody Journal journal
    ) {

        return journalRepository.save(
                journal
        );
    }
}