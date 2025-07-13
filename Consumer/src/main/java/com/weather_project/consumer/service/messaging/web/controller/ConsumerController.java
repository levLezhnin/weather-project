package com.weather_project.consumer.service.messaging.web.controller;

import com.weather_project.consumer.service.messaging.consumer.WeatherConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ConsumerController {

    private final WeatherConsumer weatherConsumer;

    public void consume() {
        CompletableFuture.runAsync(weatherConsumer);
    }
}
