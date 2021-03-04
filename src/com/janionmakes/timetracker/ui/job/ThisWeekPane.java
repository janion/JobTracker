package com.janionmakes.timetracker.ui.job;

import java.util.Map;
import java.util.Map.Entry;

import com.janionmakes.timetracker.analysis.job.JobAnalysis;
import com.janionmakes.timetracker.ui.HoursFormatter;
import com.janionmakes.timetracker.ui.utility.LayoutUtility;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ThisWeekPane {

    private TitledPane node;
    private HoursFormatter formatter = new HoursFormatter();

    public ThisWeekPane(JobAnalysis analysis) {

        // Progress bar
        ProgressBar progressBar = new ProgressBar(analysis.getCompletedHoursThisWeek() / analysis.getExpectedHoursThisWeek());
        HBox hbox = new HBox(progressBar);
        progressBar.prefWidthProperty().bind(hbox.widthProperty());
        BorderPane progressBox = new BorderPane(hbox);
        progressBox.setLeft(new Label("Progress: "));

        // Completed hours box
        Label completedHours = new Label(
                String.format("%s of %s hours completed", formatter.formatHours(analysis.getCompletedHoursThisWeek()),
                        formatter.formatHours(analysis.getExpectedHoursThisWeek())));
        HBox completedHoursBox = new HBox(completedHours);

        // Hours box
        Label remainingHours = new Label(String.format("%s hours remaining",
                formatter.formatHours(analysis.getRemainingHoursThisWeek())));
        Label remainingHoursPerDay = new Label(String.format("(%s per day)",
                formatter.formatHours(analysis.getRemainingHoursPerDayThisWeek())));
        HBox remainingHoursBox = LayoutUtility.separateNodesHorizontally(remainingHours, remainingHoursPerDay);

        // Imbalance box
        BorderPane imbalanceBox = new BorderPane();
        imbalanceBox.setTop(new Label("Imbalance:"));
        VBox imbalances = new VBox();
        Map<String, Double> imbalanceMap = analysis.getImbalances();
        if (analysis.getJob().getWorkpackages().size() > 1) {
            for (Entry<String, Double> entry : imbalanceMap.entrySet()) {
                HBox row = new HBox(new Label(entry.getKey()), new Label(formatter.formatHours(entry.getValue())));
                imbalances.getChildren().add(row);
            }
        } else {
            imbalances.getChildren().add(new Label("No imbalnce in work packages."));
        }
        imbalanceBox.setBottom(imbalances);

        VBox vbox = new VBox(progressBox, completedHoursBox, remainingHoursBox);
        if (analysis.getJob().getWorkpackages().size() > 1) {
            vbox.getChildren().add(imbalanceBox);
        }
        node = new TitledPane("This Week", new BorderPane(vbox));
    }

    public TitledPane getNode() {
        return node;
    }

}
