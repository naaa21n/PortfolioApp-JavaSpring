package com.example.portfolioapi.controller;

import com.example.portfolioapi.entity.Learning;
import com.example.portfolioapi.repository.LearningRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learnings")
@CrossOrigin(origins = "http://localhost:3000")
public class LearningController {

    private final LearningRepository learningRepository;

    public LearningController(LearningRepository learningRepository) {
        this.learningRepository = learningRepository;
    }

    // 全取得
    @GetMapping
    public List<Learning> getLearnings() {
        return learningRepository.findAll();
    }

    // 追加
    @PostMapping
    public List<Learning> addLearning(@RequestBody Learning learning) {
        learningRepository.save(learning);
        return learningRepository.findAll();
    }

    // 削除
    @DeleteMapping("/{id}")
    public List<Learning> deleteLearning(@PathVariable String id) {
        learningRepository.deleteById(id);
        return learningRepository.findAll();
    }

    // 完了
    @PutMapping("/{id}/done")
    public List<Learning> completeLearning(@PathVariable String id) {
        Learning learning = learningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning not found: " + id));

        learning.setDone(true);
        learningRepository.save(learning);

        return learningRepository.findAll();
    }
}