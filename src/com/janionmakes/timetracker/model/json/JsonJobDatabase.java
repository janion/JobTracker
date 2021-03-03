package com.janionmakes.timetracker.model.json;

import java.util.ArrayList;
import java.util.List;

public class JsonJobDatabase {

    private List<JsonJob> jobs = new ArrayList<>();

    public List<JsonJob> getJobs() {
        return new ArrayList<>(jobs);
    }

    public void setJobs(List<JsonJob> jobs) {
        this.jobs = new ArrayList<>(jobs);
    }

}
