package com.janionmakes.timetracker.model;

import java.io.File;
import java.util.List;

import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.JobDatabase;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JobManager {

    private JobDatabase jobDatabase = new JobDatabase();
    
    public void loadJobs(File jsonFile) {
        jobDatabase = new JobDatabase();
        // Load jobs from file
        throw new NotImplementedException();
    }

    public List<Job> getJobs() {
        return jobDatabase.getJobs();
    }

    public void addJob(Job job) {
        jobDatabase.addJob(job);
    }

}
