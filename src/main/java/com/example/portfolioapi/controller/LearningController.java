package com.example.portfolioapi.controller;

// Learningエンティティ
import com.example.portfolioapi.entity.Learning;

// Learningテーブル操作用Repository
import com.example.portfolioapi.repository.learning.LearningRecordRepository;

// Spring MVC
import org.springframework.web.bind.annotation.*;

// List用
import java.util.List;

// REST API Controller
@RestController

// このControllerの共通URL
// 例:
// /api/learnings
@RequestMapping("/api/learnings")

// Next.jsからのアクセス許可
public class LearningController {

    // =========================
    // Repository
    // =========================

    // Learningテーブル操作
    private final LearningRecordRepository learningRepository;

    // =========================
    // Constructor Injection
    // =========================

    // Springが自動でRepositoryを注入
    public LearningController(
            LearningRecordRepository learningRepository
    ) {

        this.learningRepository =
                learningRepository;
    }

    // =========================
    // 学習データ一覧取得
    // =========================

    // GET:
    // /api/learnings
    @GetMapping

    public List<Learning> getLearnings() {

        // learningテーブル全件取得
        //
        // SQLイメージ:
        // SELECT * FROM learning;
        return learningRepository.findAll();
    }

    // =========================
    // 学習データ追加
    // =========================

    // POST:
    // /api/learnings
    @PostMapping

    public List<Learning> addLearning(
            @RequestBody Learning learning
    ) {

        // JSONデータをDB保存
        //
        // SQLイメージ:
        // INSERT INTO learning ...
        learningRepository.save(learning);

        // 保存後の一覧返却
        return learningRepository.findAll();
    }

    // =========================
    // 学習データ削除
    // =========================

    // DELETE:
    // /api/learnings/1
    @DeleteMapping("/{id}")

    public List<Learning> deleteLearning(
            @PathVariable String id
    ) {

        // 指定ID削除
        //
        // SQLイメージ:
        // DELETE FROM learning
        // WHERE id = ?
        learningRepository.deleteById(id);

        // 削除後一覧返却
        return learningRepository.findAll();
    }
}


//learning(学習記録) を管理するAPI Controller
//学習データのCRUD担当

//このままだと全ユーザーのlearningを返してしまう危険があるので改良すべき