package com.janionmakes.timetracker.model.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Workpackage {

    private String name;
    private double fte;

    private List<WorkDay> workDays;
    private List<WorkpackageVariation> variations;

    public Workpackage(String name, double fte) {
        this(name, fte, Collections.emptyList(), Collections.emptyList());
    }

    public Workpackage(String name, double fte, List<WorkDay> workDays, List<WorkpackageVariation> variations) {
        this.name = name;
        this.fte = fte;
        this.workDays = new ArrayList<>(workDays);
        this.variations = new ArrayList<>(variations);
    }

    public String getName() {
        return name;
    }

    public double getFte() {
        return fte;
    }

    public List<WorkDay> getWorkDays() {
        return new ArrayList<>(workDays);
    }
    
    public List<WorkpackageVariation> getVariations() {
        return new ArrayList<>(variations);
    }

    public void addWorkDay(WorkDay workDay) {
        workDays.add(workDay);
    }

    public void addVariation(WorkpackageVariation variation) {
        variations.add(variation);
    }

}
