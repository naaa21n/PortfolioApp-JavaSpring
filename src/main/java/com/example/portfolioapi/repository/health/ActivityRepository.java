package com.example.portfolioapi.repository.health;

import com.example.portfolioapi.entity.health.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository
        extends JpaRepository<Activity, String> {

    Optional<Activity> findByDate(
            String date
    );
}