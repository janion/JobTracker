package com.janionmakes.timetracker.model.job;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class WorkDay {

    public static final String UNASSIGNED = "Unassigned";
    public static final String ANNUAL_LEAVE = "Annual Leave";

    private LocalDate date;
    private Map<String, Double> hours;

    public WorkDay(LocalDate date) {
        this.date = date;
        this.hours = new LinkedHashMap<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public double getHours(String workpackageName) {
        return hours.getOrDefault(workpackageName, 0.0);
    }
    
    public double getUnassignedHours() {
        return getHours(UNASSIGNED);
    }
    
    public double getAnnualLeaveHours() {
        return getHours(ANNUAL_LEAVE);
    }
    
    public void setHours(String workpackageName, double workTime) {
        hours.put(workpackageName, workTime);
    }
    
    public void setUnassignedHours(double workTime) {
        setHours(UNASSIGNED, workTime);
    }
    
    public void setAnnualLeaveHours(double workTime) {
        setHours(ANNUAL_LEAVE, workTime);
    }

}
