package com.weather_project.producer.service.dto;

import com.google.gson.Gson;
import com.weather_project.producer.model.domain.City;
import com.weather_project.producer.model.domain.DateParser;
import com.weather_project.producer.model.domain.WeatherData;
import com.weather_project.producer.model.domain.WeatherState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.util.Properties;

@Data
@AllArgsConstructor
@Builder
public class WeatherDataDTO {

    private String weatherState;
    private int temperature;
    private String city;
    private String fixationTime;

    public static WeatherData toDomain(WeatherDataDTO weatherDataDTO) throws ParseException {
        return WeatherData.builder()
                .weatherState(WeatherState.valueOf(weatherDataDTO.getWeatherState()))
                .city(City.valueOf(weatherDataDTO.getCity()))
                .temperature(weatherDataDTO.getTemperature())
                .fixationTime(DateParser.parseDate(weatherDataDTO.getFixationTime()))
                .build();
    }

    public static WeatherDataDTO fromJson(String json) {
        Gson gson = new Gson();
        Properties data = gson.fromJson(json, Properties.class);
        return WeatherDataDTO.builder()
                .weatherState(data.getProperty("weatherState"))
                .city(data.getProperty("city"))
                .temperature(Integer.parseInt(data.getProperty("temperature")))
                .fixationTime(data.getProperty("fixationTime"))
                .build();
    }

    public static WeatherDataDTO toDTO(WeatherData weatherData) {
        return WeatherDataDTO.builder()
                .weatherState(weatherData.getWeatherState().getWeatherStateName())
                .temperature(weatherData.getTemperature())
                .city(weatherData.getCity().getCityName())
                .fixationTime(DateParser.dateToString(weatherData.getFixationTime()))
                .build();
    }

    @Override
    public String toString() {
        return "WeatherDTO: { "
                + "WeatherState: " + weatherState + "; "
                + "Temperature: " + temperature + "; "
                + "City: " + city + "; "
                + "FixationTime: " + fixationTime + " }";
    }
}
