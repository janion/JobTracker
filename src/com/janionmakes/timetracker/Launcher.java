package com.janionmakes.timetracker;

import java.time.LocalDate;

import com.janionmakes.timetracker.analysis.job.JobAnalyser;
import com.janionmakes.timetracker.analysis.job.JobAnalysis;
import com.janionmakes.timetracker.model.JobManager;
import com.janionmakes.timetracker.model.job.Job;
import com.janionmakes.timetracker.model.job.JobVariation;
import com.janionmakes.timetracker.model.job.WorkDay;
import com.janionmakes.timetracker.model.job.Workpackage;
import com.janionmakes.timetracker.ui.job.JobView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        JobManager jobManager = new JobManager();
        
        Job job = createJob();
        jobManager.addJob(job);
        
        JobAnalysis analysis = new JobAnalyser().analyseJob(job);
        
        stage.setScene(new Scene(new JobView(analysis).getNode()));
        stage.show();
    }
    
    private Job createJob() {
        Job job = new Job("My Job", 37.5, 25, new JobVariation(LocalDate.of(2021, 2, 26), 1.0));
        job.addWorkpackage(new Workpackage("Project1", 1.0));

        job.addWorkDay(createFullWorkDay(LocalDate.of(2021, 3, 4)));
        job.addWorkDay(createFullWorkDay(LocalDate.of(2021, 3, 3)));
        job.addWorkDay(createFullWorkDay(LocalDate.of(2021, 3, 2)));
        job.addWorkDay(createWorkDay(LocalDate.of(2021, 3, 1), 8.25));
        
        return job;
    }
    
    private WorkDay createFullWorkDay(LocalDate date) {
        return createWorkDay(date, 7.5);
    }
    
    private WorkDay createWorkDay(LocalDate date, double hours) {
        WorkDay day = new WorkDay(date);
        day.setHours("Project1", hours);
        day.setComplete();
        return day;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
