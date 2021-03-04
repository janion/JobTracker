package com.janionmakes.timetracker.model.job;

import java.time.LocalDate;

public class JobVariation {

    private LocalDate startDate;
    private double fte;
    
    public JobVariation(LocalDate startDate, double fte) {
        this.startDate = startDate;
        this.fte = fte;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public double getFte() {
        return fte;
    }

}
