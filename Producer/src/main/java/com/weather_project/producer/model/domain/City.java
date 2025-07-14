package com.weather_project.producer.model.domain;

public enum City {
    MOSCOW("Moscow"),
    SAINT_PETERSBURG("Saint-Petersburg"),
    EKATERINBURG("Ekaterinburg");

    private final String cityName;

    public String getCityName() {
        return cityName;
    }

    City(String cityName) {
        this.cityName = cityName;
    }
}
