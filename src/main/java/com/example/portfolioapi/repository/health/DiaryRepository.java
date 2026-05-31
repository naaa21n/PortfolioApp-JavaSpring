package com.example.portfolioapi.repository.health;

import com.example.portfolioapi.entity.health.Diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository
        extends JpaRepository<Diary, String> {

    List<Diary> findByDate(
            String date
    );

}