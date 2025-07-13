package com.weather_project.producer.service.messaging.web.controller;

import com.weather_project.producer.service.messaging.producer.WeatherProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherProducer weatherProducer;

    public void sendWeatherData() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        scheduledExecutorService.scheduleAtFixedRate(weatherProducer::sendReport, 0, 14, TimeUnit.SECONDS);
    }
}
