package com.janionmakes.timetracker.model.job;

import java.util.ArrayList;
import java.util.List;

public class JobDatabase {

    private List<Job> jobs = new ArrayList<>();

    public List<Job> getJobs() {
        return new ArrayList<>(jobs);
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

}
