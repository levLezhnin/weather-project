package com.weather_project.producer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class WeatherData {

    private WeatherState weatherState;
    private int temperature;
    private City city;
    private Date fixationTime;

    @Override
    public String toString() {
        return "WeatherData: { WeatherState: " + weatherState + ";"
                + "Temperature: " + temperature + ";"
                + "City: " + city + ";"
                + "FixationTime: " + fixationTime + " }";
    }
}
