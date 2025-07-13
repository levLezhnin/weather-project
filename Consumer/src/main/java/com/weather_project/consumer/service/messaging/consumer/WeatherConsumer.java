package com.weather_project.consumer.service.messaging.consumer;


import com.google.gson.Gson;
import com.weather_project.consumer.config.ConsumerProperties;
import com.weather_project.consumer.model.domain.WeatherData;
import com.weather_project.consumer.service.dto.WeatherDataDTO;
import com.weather_project.statistics.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
public class WeatherConsumer implements Runnable {

    private final KafkaConsumer<String, Object> kafkaConsumer;

    public WeatherConsumer() {

        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ConsumerProperties.BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, ConsumerProperties.GROUP_ID);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, ConsumerProperties.AUTO_OFFSET_RESET);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ConsumerProperties.KEY_DESERIALIZER);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ConsumerProperties.VALUE_DESERIALIZER);
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, ConsumerProperties.SPRING_JSON_TRUSTED_PACKAGES);

        this.kafkaConsumer = new KafkaConsumer<>(properties);
    }


    @Override
    public void run() {

        List<WeatherData> data = new ArrayList<>();
        kafkaConsumer.subscribe(Collections.singleton(ConsumerProperties.TOPIC));

        while (true) {
            var reports = kafkaConsumer.poll(Duration.ofSeconds(7));

            for (var record : reports) {
                System.out.println(record.value());
                WeatherDataDTO event = WeatherDataDTO.fromJson((String) record.value());
                log.info("Received: {}", event);
                try {
                    data.add(WeatherDataDTO.toDomain(event));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

            Statistics.getWeeklyStatistics(List.copyOf(data));
            data.clear();
        }
    }
}
