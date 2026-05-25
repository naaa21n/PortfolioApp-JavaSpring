package com.example.portfolioapi.repository;

import com.example.portfolioapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}