package com.janionmakes.timetracker.model.json;

import java.util.Date;

public class JsonWorkDay {

    private Date date;
    private double hours;

    public JsonWorkDay(Date date) {
        this(date, 0);
    }

    public JsonWorkDay(Date date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

}
