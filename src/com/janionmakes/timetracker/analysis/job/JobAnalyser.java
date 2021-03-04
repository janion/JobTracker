package com.janionmakes.timetracker.analysis.job;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.JobVariation;
import com.janionmakes.timetracker.model.job.WorkDay;

public class JobAnalyser {

    public JobAnalysis analyseJob(Job job) {
        JobAnalysis analysis = new JobAnalysis(job);
        new EmptyDayAdder().fillInMissingDays(job);

        LocalDate today = LocalDate.now();

        List<JobVariation> jobVariations = job.getVariations();
        JobVariation currentVariation = jobVariations.get(0);
        JobVariation nextVariation = jobVariations.size() == 1 ? null : jobVariations.get(1);
        int nextVariationIndex = 1;

        double weeklyHours = job.getWeeklyHours() * currentVariation.getFte();
        double dailyHours = weeklyHours / 5;
        boolean todayIsComplete = false;

        for (WorkDay day : job.getSortedWorkDays()) {
            LocalDate dayDate = day.getDate();
            if (dayDate.isAfter(today)) {
                break;
            }

            if (nextVariation != null && !dayDate.isBefore(nextVariation.getStartDate())) {
                nextVariationIndex++;
                currentVariation = nextVariation;
                nextVariation = jobVariations.size() >= nextVariationIndex ? null
                        : jobVariations.get(nextVariationIndex);

                // TODO mid week hours changes
                weeklyHours = job.getWeeklyHours() * currentVariation.getFte();
                dailyHours = weeklyHours / 5;
            }

            double hoursWorkedOnDay = 0;
            hoursWorkedOnDay += day.getTotalHours();
            hoursWorkedOnDay += day.getUnassignedHours();
            hoursWorkedOnDay += day.getAnnualLeaveHours();

            double overtime = hoursWorkedOnDay - dailyHours;
            analysis.addOvertimeAccrued(overtime);

            boolean isThisWeek = isThisWeek(dayDate, today);
            if (isThisWeek) {
                analysis.addExpectedHoursThisWeek(dailyHours);
                analysis.addCompletedHoursThisWeek(hoursWorkedOnDay);
            }
            if (dayDate.isEqual(today)) {
                analysis.setCompletedHoursToday(hoursWorkedOnDay);
                analysis.setTodayFinished(day.isComplete());
                todayIsComplete = day.isComplete();
            }

            analysis.addAnnualLeaveDaysTaken(day.getAnnualLeaveHours());
            // TODO add annual leave accrual
        }
        
        // Fill in expected hours this week
        LocalDate eachDate = today.plusDays(1);
        while (!DayOfWeek.SATURDAY.equals(eachDate.getDayOfWeek())) {
            if (nextVariation != null && !eachDate.isBefore(nextVariation.getStartDate())) {
                nextVariationIndex++;
                currentVariation = nextVariation;
                nextVariation = jobVariations.size() >= nextVariationIndex ? null
                        : jobVariations.get(nextVariationIndex);

                // TODO mid week hours changes
                weeklyHours = job.getWeeklyHours() * currentVariation.getFte();
                dailyHours = weeklyHours / 5;
            }

            analysis.addExpectedHoursThisWeek(dailyHours);
            eachDate = eachDate.plusDays(1);
        }

        analysis.setCurrentVariation(currentVariation);
        int remainingDays = 5 - today.getDayOfWeek().ordinal();
        if (todayIsComplete) {
            remainingDays--;
        }
        analysis.setRemainingDaysThisWeek(remainingDays);

        return analysis;
    }

    private boolean isThisWeek(LocalDate dayDate, LocalDate today) {
        long daysBetween = ChronoUnit.DAYS.between(dayDate, today);
        int dayOfWeek = today.getDayOfWeek().ordinal(); // Monday == 0

        return daysBetween <= dayOfWeek;
    }

}
