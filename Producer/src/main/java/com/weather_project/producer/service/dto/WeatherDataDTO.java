package com.weather_project.producer.service.dto;

import com.weather_project.producer.model.domain.City;
import com.weather_project.producer.model.domain.DateParser;
import com.weather_project.producer.model.domain.WeatherData;
import com.weather_project.producer.model.domain.WeatherState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.text.ParseException;

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

    public static WeatherDataDTO toDTO(WeatherData weatherData) {
        return WeatherDataDTO.builder()
                .weatherState(String.valueOf(weatherData.getWeatherState()))
                .temperature(weatherData.getTemperature())
                .city(weatherData.getCity().name())
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
