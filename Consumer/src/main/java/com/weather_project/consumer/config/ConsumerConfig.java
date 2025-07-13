package com.weather_project.consumer.config;

import com.weather_project.consumer.service.messaging.consumer.WeatherConsumer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Bean
    public WeatherConsumer weatherConsumer() {
        return new WeatherConsumer();
    }

}
