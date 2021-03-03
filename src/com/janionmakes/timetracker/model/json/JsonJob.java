package com.janionmakes.timetracker.model.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonJob {
    
    private String name;
    private double weeklyHours;
    private int annualLeaveDays;
    
    private List<JsonWorkpackage> workpackages;
    private List<JsonJobVariation> variations;

    public JsonJob(String name, double weeklyHours, int annualLeaveDays) {
        this(name, weeklyHours, annualLeaveDays, Collections.emptyList(), Collections.emptyList());
    }

    public JsonJob(String name, double weeklyHours, int annualLeaveDays, List<JsonWorkpackage> workpackages, List<JsonJobVariation> variations) {
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

    public List<JsonWorkpackage> getWorkpackages() {
        return new ArrayList<>(workpackages);
    }

    public List<JsonJobVariation> getVariations() {
        return new ArrayList<>(variations);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeeklyHours(double weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public void setAnnualLeaveDays(int annualLeaveDays) {
        this.annualLeaveDays = annualLeaveDays;
    }

    public void setWorkpackages(List<JsonWorkpackage> workpackages) {
        this.workpackages = new ArrayList<>(workpackages);
    }

    public void setVariations(List<JsonJobVariation> variations) {
        this.variations = new ArrayList<>(variations);
    }

}
