package com.example.portfolioapi.repository.health;

import com.example.portfolioapi.entity.health.Health;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

public interface HealthRepository
        extends JpaRepository<Health, String> {

    Optional<Health> findByDate(LocalDate date);

    List<Health> findAll();
}