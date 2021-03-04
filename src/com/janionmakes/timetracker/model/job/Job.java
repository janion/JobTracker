package com.janionmakes.timetracker.model.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Job {
    
    private String name;
    private double weeklyHours;
    private int annualLeaveAllowance;
    
    private List<Workpackage> workpackages;
    private List<JobVariation> variations;
    private List<WorkDay> workDays;

    private boolean daysSorted;
    private boolean variationsSorted;

    public Job(String name, double weeklyHours, int annualLeaveAllowance, JobVariation startingVariation) {
        this(name, weeklyHours, annualLeaveAllowance, Collections.emptyList(), Collections.singletonList(startingVariation), Collections.emptyList());
    }

    public Job(String name, double weeklyHours, int annualLeaveAllowance, List<Workpackage> workpackages, List<JobVariation> variations,  List<WorkDay> workDays) {
        this.name = name;
        this.weeklyHours = weeklyHours;
        this.annualLeaveAllowance = annualLeaveAllowance;
        this.workpackages = new ArrayList<>(workpackages);
        this.variations = new ArrayList<>(variations);
        this.workDays = new ArrayList<>(workDays);
        
        daysSorted = false;
        variationsSorted = false;
    }

    public String getName() {
        return name;
    }

    public double getWeeklyHours() {
        return weeklyHours;
    }
    
    public int getAnnualLeaveAllowance() {
        return annualLeaveAllowance;
    }

    public List<Workpackage> getWorkpackages() {
        return new ArrayList<>(workpackages);
    }
    
    public List<JobVariation> getVariations() {
        if (!variationsSorted) {
            variations.sort(Comparator.comparing(JobVariation::getStartDate));
            variationsSorted = true;
        }
        return new ArrayList<>(variations);
    }
    
    public List<WorkDay> getSortedWorkDays() {
        if (!daysSorted) {
            workDays.sort(Comparator.comparing(WorkDay::getDate));
            daysSorted = true;
        }
        return new ArrayList<>(workDays);
    }
    
    public void addWorkpackage(Workpackage workpackage) {
        workpackages.add(workpackage);
    }

    public void addVariation(JobVariation variation) {
        variationsSorted = false;
        variations.add(variation);
    }

    public void addWorkDay(WorkDay workDay) {
        daysSorted = false;
        workDays.add(workDay);
    }

}
