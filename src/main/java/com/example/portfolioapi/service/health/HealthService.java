package com.example.portfolioapi.service.health;

import com.example.portfolioapi.repository.health.HealthRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    private final HealthRecordRepository healthRepository;

    public HealthService(
            HealthRecordRepository healthRepository
    ) {
        this.healthRepository = healthRepository;
    }

}