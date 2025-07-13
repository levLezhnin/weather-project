package com.weather_project.producer.model.domain;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

public class DataFactory {
    private static Random random;
    private static final int WEATHER_TYPES_SIZE, TEMPERATURE_UPPERBOUND;

    static {
        WEATHER_TYPES_SIZE = WeatherState.values().length;
        TEMPERATURE_UPPERBOUND = 36;
    }

    public DataFactory() {
        random = new Random();
    }

    public WeatherData basicWeatherDataInCity(City city) throws ParseException {

        int index = random.nextInt(WEATHER_TYPES_SIZE);
        WeatherState weatherState = WeatherState.values()[index];
        int temperature = random.nextInt(TEMPERATURE_UPPERBOUND);
        Date date = DateParser.parseDate(CalendarHelper.getDate());

        return WeatherData.builder()
                .weatherState(weatherState)
                .temperature(temperature)
                .city(city)
                .fixationTime(date)
                .build();
    }
}
