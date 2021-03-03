package com.janionmakes.timetracker;

import com.janionmakes.timetracker.model.JobManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        JobManager jobManager = new JobManager();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
