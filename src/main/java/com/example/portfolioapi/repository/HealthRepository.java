package com.example.portfolioapi.repository;

import com.example.portfolioapi.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, String> {
}