package com.janionmakes.timetracker.ui;

public class HoursFormatter {

    private static final String HOURS_FORMAT = "%d:%02d";

    public String formatHours(double hours) {
        return String.format(HOURS_FORMAT, (int) hours, (int) hours % 60);
    }

}
