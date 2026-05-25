package com.example.portfolioapi.controller;

import com.example.portfolioapi.entity.Task;
import com.example.portfolioapi.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public List<Task> addTask(@RequestBody Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }

    @DeleteMapping("/tasks/{id}")
    public List<Task> deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
        return taskRepository.findAll();
    }

    

    @PutMapping("/tasks/{id}/done")
    public List<Task> completeTask(@PathVariable String id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(true);
        taskRepository.save(task);
        return taskRepository.findAll();
    }
}