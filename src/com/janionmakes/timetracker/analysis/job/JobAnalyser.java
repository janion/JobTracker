package com.janionmakes.timetracker.analysis.job;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.JobVariation;
import com.janionmakes.timetracker.model.job.WorkDay;
import com.janionmakes.timetracker.model.job.Workpackage;

public class JobAnalyser {

    public JobAnalysis analyseJob(Job job) {
        JobAnalysis analysis = new JobAnalysis(job);
        
        LocalDate today = LocalDate.now();

        List<JobVariation> jobVariations = job.getVariations();
        JobVariation currentVariation = jobVariations.get(0);
        JobVariation nextVariation = jobVariations.size() == 1 ? null : jobVariations.get(1);
        int nextVariationIndex = 1;

        double weeklyHours = job.getWeeklyHours() * currentVariation.getFte();
        double dailyHours = weeklyHours / 5;

        for (WorkDay day : job.getWorkDays()) {
            LocalDate dayDate = day.getDate();
            if (dayDate.isAfter(today)) {
                break;
            }
            
            if (nextVariation != null && !dayDate.isBefore(nextVariation.getStartDate())) {
                nextVariationIndex++;
                currentVariation = nextVariation;
                nextVariation = jobVariations.size() >= nextVariationIndex ? null : jobVariations.get(nextVariationIndex);

                // TODO mid week hours changes
                weeklyHours = job.getWeeklyHours() * currentVariation.getFte();
                dailyHours = weeklyHours / 5;
            }
            
            double hoursWorkedOnDay = 0;
            for (Workpackage workpackage : job.getWorkpackages()) {
                hoursWorkedOnDay += day.getHours(workpackage.getName());
                hoursWorkedOnDay += day.getUnassignedHours();
                hoursWorkedOnDay += day.getAnnualLeaveHours();
            }
            
            double overtime = hoursWorkedOnDay - dailyHours;
            analysis.addOvertimeAccrued(overtime);
            
            boolean isThisWeek = isThisWeek(dayDate, today);
            if (isThisWeek) {
                analysis.addExpectedHoursThisWeek(dailyHours);
                analysis.addCompletedHoursThisWeek(hoursWorkedOnDay);
            }

            analysis.addAnnualLeaveDaysTaken(day.getAnnualLeaveHours());
            // TODO add annual leave accrual
        }

        analysis.setCurrentVariation(currentVariation);
        analysis.setRemainingDaysThisWeek(5 - today.getDayOfWeek().ordinal());
        analysis.setContractedHoursToday(currentVariation.getFte() * job.getWeeklyHours() / 5);

        return analysis;
    }
    
    private boolean isThisWeek(LocalDate dayDate, LocalDate today) {
        long daysBetween = ChronoUnit.DAYS.between(dayDate, today);
        int dayOfWeek = today.getDayOfWeek().ordinal(); // Monday == 0
        
        return daysBetween <= dayOfWeek;
    }

}
