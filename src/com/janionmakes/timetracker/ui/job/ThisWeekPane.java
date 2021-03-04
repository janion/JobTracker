package com.janionmakes.timetracker.ui.job;

import java.util.Map;
import java.util.Map.Entry;

import com.janionmakes.timetracker.analysis.job.JobAnalysis;
import com.janionmakes.timetracker.ui.HoursFormatter;

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
        HBox progressBox = new HBox(new Label("Progress: "),
                new ProgressBar(analysis.getCompletedHoursThisWeek() / analysis.getExpectedHoursThisWeek()));

        // Completed hours box
        Label completedHours = new Label(
                String.format("%s of %s hours completed", formatter.formatHours(analysis.getCompletedHoursThisWeek()),
                        formatter.formatHours(analysis.getExpectedHoursThisWeek())));
        HBox completedHoursBox = new HBox(completedHours);

        // Hours box
        Label remainingHours = new Label(String.format("%s hours remaining (%s per day)",
                formatter.formatHours(analysis.getRemainingHoursThisWeek()),
                formatter.formatHours(analysis.getRemainingHoursPerDayThisWeek())));
        // Maybe change to border pane or use expansion trick
        HBox remainingHoursBox = new HBox(remainingHours);

        // Imbalance box
        BorderPane imbalanceBox = new BorderPane();
        imbalanceBox.setTop(new Label("Imbalance:"));
        VBox imbalances = new VBox();
        Map<String, Double> imbalanceMap = analysis.getImbalances();
        if (!imbalanceMap.isEmpty()) {
            for (Entry<String, Double> entry : imbalanceMap.entrySet()) {
                HBox row = new HBox(new Label(entry.getKey()), new Label(formatter.formatHours(entry.getValue())));
                imbalances.getChildren().add(row);
            }
        } else {
            imbalances.getChildren().add(new Label("No imbalnce in work packages."));
        }

        VBox vbox = new VBox(progressBox, completedHoursBox, remainingHoursBox);
        if (!analysis.getJob().getWorkpackages().isEmpty()) {
            vbox.getChildren().add(imbalanceBox);
        }
        node = new TitledPane("This Week", vbox);
    }

    public TitledPane getNode() {
        return node;
    }

}
