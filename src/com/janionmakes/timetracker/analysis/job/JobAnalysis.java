package com.janionmakes.timetracker.analysis.job;

import java.util.LinkedHashMap;
import java.util.Map;

import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.JobVariation;

public class JobAnalysis {

    private Job job;

    private double completedHoursToday;
    private boolean todayFinished;
    private double completedHoursThisWeek;
    private double expectedHoursThisWeek;
    private int remainingDaysThisWeek;

    private Map<String, Double> imbalances;

    private double overtimeAccrued;

    private double annualLeaveHoursTaken;
    private double annualLeaveDaysRemaining;

    private JobVariation currentVariation;

    public JobAnalysis(Job job) {
        this.job = job;
        imbalances = new LinkedHashMap<>();
    }

    public Job getJob() {
        return job;
    }

    public double getCompletedHoursThisWeek() {
        return completedHoursThisWeek;
    }

    public double getExpectedHoursThisWeek() {
        return expectedHoursThisWeek;
    }

    public double getRemainingHoursThisWeek() {
        return expectedHoursThisWeek - completedHoursThisWeek;
    }

    public double getRemainingHoursPerDayThisWeek() {
        if (remainingDaysThisWeek == 0) {
            return 0;
        }
        return (expectedHoursThisWeek + getHoursToDiscountToday() - completedHoursThisWeek) / remainingDaysThisWeek;
    }

    public Map<String, Double> getImbalances() {
        return new LinkedHashMap<>(imbalances);
    }

    public double getOvertimeAccrued() {
        return overtimeAccrued;
    }

    public double getRemainingHoursPerDayThisWeekForZeroOvertime() {
        if (remainingDaysThisWeek == 0) {
            return 0;
        }
        return (expectedHoursThisWeek + getHoursToDiscountToday() - (overtimeAccrued + completedHoursThisWeek))
                / remainingDaysThisWeek;
    }

    public double getAnnualLeaveDaysTaken() {
        return annualLeaveHoursTaken;
    }

    public double getAnnualLeaveDaysRemaining() {
        return annualLeaveDaysRemaining;
    }

    public int getRemainingDaysThisWeek() {
        return remainingDaysThisWeek;
    }

    public JobVariation getCurrentVariation() {
        return currentVariation;
    }
    
    public boolean isTodayFinished() {
        return todayFinished;
    }

    public void setCompletedHoursToday(double completedHoursToday) {
        this.completedHoursToday = completedHoursToday;
    }

    void addCompletedHoursThisWeek(double completedHoursThisWeek) {
        this.completedHoursThisWeek += completedHoursThisWeek;
    }

    void addExpectedHoursThisWeek(double expectedHoursThisWeek) {
        this.expectedHoursThisWeek += expectedHoursThisWeek;
    }

    void setImbalances(Map<String, Double> imbalances) {
        this.imbalances = new LinkedHashMap<>(imbalances);
    }

    void addOvertimeAccrued(double overtimeAccrued) {
        this.overtimeAccrued += overtimeAccrued;
    }

    void addAnnualLeaveDaysTaken(double annualLeaveHoursTaken) {
        this.annualLeaveHoursTaken += annualLeaveHoursTaken;
    }

    void setAnnualLeaveDaysRemaining(double annualLeaveDaysRemaining) {
        this.annualLeaveDaysRemaining = annualLeaveDaysRemaining;
    }

    void setRemainingDaysThisWeek(int remainingDaysThisWeek) {
        this.remainingDaysThisWeek = remainingDaysThisWeek;
    }

    void setCurrentVariation(JobVariation currentVariation) {
        this.currentVariation = currentVariation;
    }
    
    void setTodayFinished(boolean todayFinished) {
        this.todayFinished = todayFinished;
    }
    
    private double getHoursToDiscountToday() {
        return todayFinished ? 0 : completedHoursToday;
    }

}
