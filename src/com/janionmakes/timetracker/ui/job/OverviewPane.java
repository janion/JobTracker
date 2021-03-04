package com.janionmakes.timetracker.ui.job;

import com.janionmakes.timetracker.analysis.job.JobAnalysis;
import com.janionmakes.timetracker.ui.HoursFormatter;
import com.janionmakes.timetracker.ui.utility.LayoutUtility;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OverviewPane {

    private TitledPane node;
    private HoursFormatter formatter = new HoursFormatter();

    public OverviewPane(JobAnalysis analysis) {

        // Current FTE & hours
        Label proRatedHours = new Label(String.format("Hours per week: %s",
                formatter.formatHours(analysis.getCurrentVariation().getFte() * analysis.getJob().getWeeklyHours())));
        Label fullHours = new Label(String.format("%.0f%% of %s", analysis.getCurrentVariation().getFte() * 100,
                formatter.formatHours(analysis.getJob().getWeeklyHours())));
        HBox hoursBox = LayoutUtility.separateNodesHorizontally(proRatedHours, fullHours);

        // Annual leave allowance & taken
        Label totalLeave = new Label(
                String.format("Annual leave: %s days", analysis.getJob().getAnnualLeaveAllowance()));
        Label availableLeave = new Label(String.format("%.1f days taken, %.1f days remaining",
                analysis.getAnnualLeaveDaysTaken(), analysis.getAnnualLeaveDaysRemaining()));
        HBox leaveBox = LayoutUtility.separateNodesHorizontally(totalLeave, availableLeave);

        // Overtime accrued
        Label overtime = new Label(
                String.format("Overtime accrued: %s", formatter.formatHours(analysis.getOvertimeAccrued())));
        HBox overtimeBox = new HBox(overtime);

        // Hours to make up to net zero
        Label overtimeMakeUp = new Label(String.format("To return to net zero overtime: %s per day this week",
                formatter.formatHours(analysis.getRemainingHoursPerDayThisWeekForZeroOvertime())));
        HBox overtimeMakeUpBox = new HBox(overtimeMakeUp);

        node = new TitledPane("Overview", new BorderPane(new VBox(hoursBox, leaveBox, overtimeBox, overtimeMakeUpBox)));
    }

    public TitledPane getNode() {
        return node;
    }

}
