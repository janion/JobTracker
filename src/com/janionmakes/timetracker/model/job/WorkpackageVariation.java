package com.janionmakes.timetracker.model.job;

import java.util.Date;

public class WorkpackageVariation {

    private Date startDate;
    private double fte;
    
    public WorkpackageVariation(Date startDate, double fte) {
        this.startDate = startDate;
        this.fte = fte;
    }
    public Date getStartDate() {
        return startDate;
    }
    public double getFte() {
        return fte;
    }

}
