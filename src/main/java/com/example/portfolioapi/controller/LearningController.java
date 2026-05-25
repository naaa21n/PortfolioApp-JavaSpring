package com.example.portfolioapi.controller;

import com.example.portfolioapi.entity.Task;
import com.example.portfolioapi.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LearningController {

    private final TaskRepository taskRepository;

    public LearningController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/learnings")
    public List<Task> getLearnings() {
        return taskRepository.findAll();
    }

    @PostMapping("/learnings")
    public List<Task> addLearning(@RequestBody Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }

    @DeleteMapping("/learnings/{id}")
    public List<Task> deleteLearning(@PathVariable String id) {
        taskRepository.deleteById(id);
        return taskRepository.findAll();
    }

    @PutMapping("/learnings/{id}/done")
    public List<Task> completeLearning(@PathVariable String id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(true);
        taskRepository.save(task);
        return taskRepository.findAll();
    }
}