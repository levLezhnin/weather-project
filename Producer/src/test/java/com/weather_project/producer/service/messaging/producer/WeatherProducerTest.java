package com.weather_project.producer.service.messaging.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class WeatherProducerTest {

    @Autowired
    private WeatherProducer weatherProducer;

    @Test
    public void shouldSendOrder() {

    }
}
