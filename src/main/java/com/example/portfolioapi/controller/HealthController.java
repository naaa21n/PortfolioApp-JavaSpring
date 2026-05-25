package com.example.portfolioapi.controller;

import com.example.portfolioapi.entity.Task;
import com.example.portfolioapi.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class HealthController {

    private final TaskRepository taskRepository;

    public HealthController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/healths")
    public List<Task> getHealths() {
        return taskRepository.findAll();
    }

    @PostMapping("/healths")
    public List<Task> addHealth(@RequestBody Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }

    @DeleteMapping("/healths/{id}")
    public List<Task> deleteHealth(@PathVariable String id) {
        taskRepository.deleteById(id);
        return taskRepository.findAll();
    }

    @PutMapping("/healths/{id}/done")
    public List<Task> completeHealth(@PathVariable String id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(true);
        taskRepository.save(task);
        return taskRepository.findAll();
    }
}