package com.janionmakes.timetracker.analysis.job;

import java.util.Map;

import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.JobVariation;

public class JobAnalysis {

    private Job job;

    private double completedHoursToday;
    private double contractedHoursToday;
    private double completedHoursThisWeek;
    private double expectedHoursThisWeek;
    private int remainingDaysThisWeek;
    private double remainingHoursThisWeek;

    private Map<String, Double> imbalances;

    private double overtimeAccrued;

    private double annualLeaveHoursTaken;
    private double annualLeaveDaysRemaining;

    private JobVariation currentVariation;

    public JobAnalysis(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public double getContractedHoursToday() {
        return contractedHoursToday;
    }

    public double getCompletedHoursThisWeek() {
        return completedHoursThisWeek;
    }

    public double getExpectedHoursThisWeek() {
        return expectedHoursThisWeek;
    }

    public double getRemainingHoursThisWeek() {
        return remainingHoursThisWeek;
    }

    public double getRemainingHoursPerDayThisWeek() {
        return (expectedHoursThisWeek + completedHoursToday - completedHoursThisWeek) / remainingDaysThisWeek;
    }

    public Map<String, Double> getImbalances() {
        return imbalances;
    }

    public double getOvertimeAccrued() {
        return overtimeAccrued;
    }

    public double getRemainingHoursPerDayThisWeekForZeroOvertime() {
        return (expectedHoursThisWeek + overtimeAccrued + completedHoursToday - completedHoursThisWeek)
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

    public void setCompletedHoursToday(double completedHoursToday) {
        this.completedHoursToday = completedHoursToday;
    }

    public void setContractedHoursToday(double contractedHoursToday) {
        this.contractedHoursToday = contractedHoursToday;
    }

    void addCompletedHoursThisWeek(double completedHoursThisWeek) {
        this.completedHoursThisWeek += completedHoursThisWeek;
    }

    void addExpectedHoursThisWeek(double expectedHoursThisWeek) {
        this.expectedHoursThisWeek += expectedHoursThisWeek;
    }

    void setRemainingHoursThisWeek(double remainingHoursThisWeek) {
        this.remainingHoursThisWeek = remainingHoursThisWeek;
    }

    void setImbalances(Map<String, Double> imbalances) {
        this.imbalances = imbalances;
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

    public void setCurrentVariation(JobVariation currentVariation) {
        this.currentVariation = currentVariation;
    }

}
