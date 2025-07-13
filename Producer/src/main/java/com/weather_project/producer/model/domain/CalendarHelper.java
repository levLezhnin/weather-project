package com.weather_project.producer.model.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarHelper {
    private static final Calendar calendar;
    private static final SimpleDateFormat simpleDateFormat;
    static {
        calendar = new GregorianCalendar(2020, Calendar.JANUARY, 1);
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public static void rollToNextDay() {
        calendar.add(Calendar.DATE, 1);
    }

    public static String getDate() {
        return simpleDateFormat.format(calendar.getTime());
    }
}
