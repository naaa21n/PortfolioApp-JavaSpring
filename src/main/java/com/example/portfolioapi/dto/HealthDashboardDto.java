package com.example.portfolioapi.dto;


//ダッシュボードの集計結果を返したい時に使う予定だったクラス


// =========================
// Health Dashboard DTO
// =========================
//
// 健康ダッシュボード表示用DTO
//
// フロント画面のサマリーカードへ
// 集計結果を返すために使用する
//
// 管理データ
//
// ・今日の歩数
// ・今週の平均歩数
// ・連続記録日数
// ・総記録数
//
// Controller → Frontend
// のデータ受け渡し専用クラス
//
public class HealthDashboardDto {

    // =========================
    // Dashboard Data
    // =========================

    // 今日の歩数
    private Integer todaySteps;

    // 今週の平均歩数
    private Double weeklyAverage;

    // 連続記録日数
    private Integer streakDays;

    // 総記録数
    private Long totalRecords;

    // =========================
    // Constructor
    // =========================

    public HealthDashboardDto() {
    }

    // =========================
    // Getter / Setter
    // =========================

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

//未使用（削除してもいい）