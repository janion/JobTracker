package com.janionmakes.timetracker.model.job;

import java.util.Date;

public class WorkDay {

    private Date date;
    private double hours;

    public WorkDay(Date date) {
        this(date, 0);
    }

    public WorkDay(Date date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }
    
    public void setHours(double hours) {
        this.hours = hours;
    }

}
