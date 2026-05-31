package com.example.portfolioapi.dto;

public class HealthDashboardDto {

    // 今日の歩数
    private Integer todaySteps;

    // 今週の平均歩数
    private Double weeklyAverage;

    // 連続記録日数
    private Integer streakDays;

    // 総記録数
    private Long totalRecords;

    public HealthDashboardDto() {
    }

    public Integer getTodaySteps() {
        return todaySteps;
    }

    public void setTodaySteps(Integer todaySteps) {
        this.todaySteps = todaySteps;
    }

    public Double getWeeklyAverage() {
        return weeklyAverage;
    }

    public void setWeeklyAverage(Double weeklyAverage) {
        this.weeklyAverage = weeklyAverage;
    }

    public Integer getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(Integer streakDays) {
        this.streakDays = streakDays;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}