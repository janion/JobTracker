package com.janionmakes.timetracker.ui.job;

import com.janionmakes.timetracker.analysis.job.JobAnalysis;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class JobView {

    private BorderPane node;

    public JobView(JobAnalysis analysis) {
        node = new BorderPane(new Label(analysis.getJob().getName()));

        VBox vbox = new VBox();
        vbox.getChildren().add(new ThisWeekPane(analysis).getNode());
        vbox.getChildren().add(new OverviewPane(analysis).getNode());
        
        node.setCenter(vbox);
    }

    public BorderPane getNode() {
        return node;
    }

}
