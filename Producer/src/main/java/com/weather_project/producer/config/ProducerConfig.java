package com.weather_project.producer.config;

import com.weather_project.producer.model.domain.DataFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean
    public DataFactory dataFactory() {
        return new DataFactory();
    }
}
