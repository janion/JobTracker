package com.janionmakes.timetracker.model.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Job {
    
    private String name;
    private double weeklyHours;
    private int annualLeaveDays;
    
    private List<Workpackage> workpackages;
    private List<JobVariation> variations;

    public Job(String name, double weeklyHours, int annualLeaveDays) {
        this(name, weeklyHours, annualLeaveDays, Collections.emptyList(), Collections.emptyList());
    }

    public Job(String name, double weeklyHours, int annualLeaveDays, List<Workpackage> workpackages, List<JobVariation> variations) {
        this.name = name;
        this.weeklyHours = weeklyHours;
        this.annualLeaveDays = annualLeaveDays;
        this.workpackages = new ArrayList<>(workpackages);
        this.variations = new ArrayList<>(variations);
    }

    public String getName() {
        return name;
    }

    public double getWeeklyHours() {
        return weeklyHours;
    }
    
    public int getAnnualLeaveDays() {
        return annualLeaveDays;
    }

    public List<Workpackage> getWorkpackages() {
        return new ArrayList<>(workpackages);
    }
    
    public List<JobVariation> getVariations() {
        return new ArrayList<>(variations);
    }
    
    public void addWorkpackage(Workpackage workpackage) {
        workpackages.add(workpackage);
    }

    public void addVariation(JobVariation variation) {
        variations.add(variation);
    }

}
