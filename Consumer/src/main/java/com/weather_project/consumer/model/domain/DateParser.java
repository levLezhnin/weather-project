package com.weather_project.consumer.model.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static final SimpleDateFormat simpleDateFormat;

    static {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public static Date parseDate(String data) throws ParseException {
        return simpleDateFormat.parse(data);
    }

    public static String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }
}
