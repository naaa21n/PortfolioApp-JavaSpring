package com.example.portfolioapi.repository;

import com.example.portfolioapi.entity.Learning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRepository extends JpaRepository<Learning, String> {
}