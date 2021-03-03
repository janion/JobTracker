package com.janionmakes.timetracker.model.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonWorkpackage {

    private String name;
    private double fte;

    private List<JsonWorkDay> workDays;
    private List<JsonWorkpackageVariation> variations;

    public JsonWorkpackage(String name, double fte) {
        this(name, fte, Collections.emptyList(), Collections.emptyList());
    }

    public JsonWorkpackage(String name, double fte, List<JsonWorkDay> workDays, List<JsonWorkpackageVariation> variations) {
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

    public List<JsonWorkDay> getWorkDays() {
        return new ArrayList<>(workDays);
    }

    public List<JsonWorkpackageVariation> getVariations() {
        return new ArrayList<>(variations);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFte(double fte) {
        this.fte = fte;
    }

    public void setWorkDays(List<JsonWorkDay> workDays) {
        this.workDays = new ArrayList<>(workDays);
    }

    public void setVariations(List<JsonWorkpackageVariation> variations) {
        this.variations = new ArrayList<>(variations);
    }

}
