package com.weather_project.consumer;

import com.weather_project.consumer.service.messaging.consumer.WeatherConsumer;
import com.weather_project.consumer.service.messaging.web.controller.ConsumerController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        ConsumerController consumerController = context.getBean(ConsumerController.class);
        consumerController.consume();
    }

}
