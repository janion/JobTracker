package com.janionmakes.timetracker.model.json;

import java.util.Date;

public class JsonWorkpackageVariation {

    private Date startDate;
    private double fte;
    
    public JsonWorkpackageVariation(Date startDate, double fte) {
        this.startDate = startDate;
        this.fte = fte;
    }

    public Date getStartDate() {
        return startDate;
    }

    public double getFte() {
        return fte;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFte(double fte) {
        this.fte = fte;
    }

}
