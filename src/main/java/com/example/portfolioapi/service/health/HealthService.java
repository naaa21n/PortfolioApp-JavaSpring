package com.example.portfolioapi.service.health;

import com.example.portfolioapi.repository.health.HealthRepository;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    private final HealthRepository healthRepository;

    public HealthService(
            HealthRepository healthRepository
    ) {
        this.healthRepository = healthRepository;
    }

}