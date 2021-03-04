package com.janionmakes.timetracker.ui;

public class HoursFormatter {

    private static final String HOURS_FORMAT = "%s%d:%02d";

    public String formatHours(double hours) {
        String sign = hours < 0 ? "-" : "";
        hours = Math.abs(hours);
        return String.format(HOURS_FORMAT, sign, (int) hours, (int) ((hours % 1) * 60));
    }

}
