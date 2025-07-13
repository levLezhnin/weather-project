package com.weather_project.consumer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class WeatherData {

    private String weatherState;
    private int temperature;
    private String city;
    private Date fixationTime;

    @Override
    public String toString() {
        return "WeatherData: { "
                + "WeatherState: " + weatherState + "; "
                + "Temperature: " + temperature + "; "
                + "City: " + city + "; "
                + "FixationTime: " + fixationTime + " }";
    }
}
