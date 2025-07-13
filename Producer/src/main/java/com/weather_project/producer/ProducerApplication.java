package com.weather_project.producer;

import com.weather_project.producer.service.messaging.web.controller.WeatherController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        context.getBean(WeatherController.class).sendWeatherData();
    }
}
