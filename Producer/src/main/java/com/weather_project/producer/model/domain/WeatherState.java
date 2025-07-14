package com.weather_project.producer.model.domain;

public enum WeatherState {
    SUNNY("Sunny"), RAIN("Rain"), CLOUDY("Cloudy");

    private final String weatherStateName;

    public String getWeatherStateName() {
        return weatherStateName;
    }

    WeatherState(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }
}
