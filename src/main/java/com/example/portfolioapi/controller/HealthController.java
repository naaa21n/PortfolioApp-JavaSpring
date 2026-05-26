package com.example.portfolioapi.controller;

import com.example.portfolioapi.entity.Health;
import com.example.portfolioapi.repository.HealthRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healths")
@CrossOrigin(origins = "http://localhost:3000")
public class HealthController {

    private final HealthRepository healthRepository;

    public HealthController(HealthRepository healthRepository) {
        this.healthRepository = healthRepository;
    }

    @GetMapping
    public List<Health> getHealths() {
        return healthRepository.findAll();
    }

    @PostMapping
    public List<Health> addHealth(@RequestBody Health health) {
        healthRepository.save(health);
        return healthRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Health> deleteHealth(@PathVariable String id) {
        healthRepository.deleteById(id);
        return healthRepository.findAll();
    }
}