package com.janionmakes.timetracker.model.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Workpackage {

    private String name;
    private double fte;

    private List<WorkpackageVariation> variations;

    public Workpackage(String name, double fte) {
        this(name, fte, Collections.emptyList());
    }

    public Workpackage(String name, double fte, List<WorkpackageVariation> variations) {
        this.name = name;
        this.fte = fte;
        this.variations = new ArrayList<>(variations);
    }

    public String getName() {
        return name;
    }

    public double getFte() {
        return fte;
    }
    
    public List<WorkpackageVariation> getVariations() {
        return new ArrayList<>(variations);
    }

    public void addVariation(WorkpackageVariation variation) {
        variations.add(variation);
    }

}
