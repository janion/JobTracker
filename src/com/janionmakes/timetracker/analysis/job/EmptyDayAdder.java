package com.janionmakes.timetracker.analysis.job;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.WorkDay;

public class EmptyDayAdder {

    public void fillInMissingDays(Job job) {
        LocalDate lastDay = fillInGaps(job);
        fillInUpToNow(job, lastDay);
    }

    private LocalDate fillInGaps(Job job) {
        LocalDate lastDay = job.getVariations().get(0).getStartDate();
        for (WorkDay day : job.getSortedWorkDays()) {
            if (lastDay != null) {
                long daysSinceLast = ChronoUnit.DAYS.between(lastDay, day.getDate());
                if (daysSinceLast > 1) {
                    LocalDate lastDate = lastDay;
                    while (lastDate.isBefore(day.getDate())) {
                        if (lastDate.getDayOfWeek().ordinal() < 5) {
                            job.addWorkDay(new WorkDay(lastDate));
                        }
                        lastDate = lastDate.plusDays(1);
                    }
                }
            }
            lastDay = day.getDate();
        }
        return lastDay;
    }

    private void fillInUpToNow(Job job, LocalDate lastDay) {
        LocalDate today = LocalDate.now();
        LocalDate lastDate = lastDay;
        while (lastDate.isBefore(today)) {
            LocalDate intermediateDate = lastDate.plusDays(1);
            if (intermediateDate.getDayOfWeek().ordinal() < 5) {
                job.addWorkDay(new WorkDay(intermediateDate));
            }
            lastDate = lastDate.plusDays(1);
        }
    }

}
