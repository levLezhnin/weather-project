package com.weather_project.producer.service.messaging.producer;

import com.google.gson.Gson;
import com.weather_project.producer.model.domain.CalendarHelper;
import com.weather_project.producer.model.domain.City;
import com.weather_project.producer.model.domain.DataFactory;
import com.weather_project.producer.config.ProducerProperties;
import com.weather_project.producer.model.domain.WeatherData;
import com.weather_project.producer.service.dto.WeatherDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class WeatherProducer {

    @Value("${topic.weather-report}")
    private String topic;

    private final KafkaProducer<String, Object> producer;
    private final DataFactory dataFactory;

    public WeatherProducer(DataFactory dataFactory) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ProducerProperties.BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ProducerProperties.KEY_SERIALIZER);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ProducerProperties.VALUE_SERIALIZER);
        properties.put("acks", ProducerProperties.acks);

        producer = new KafkaProducer<>(properties);

        this.dataFactory = dataFactory;
    }

    public void sendReport() {
        List<WeatherDataDTO> toSend = new ArrayList<>();
        Gson gson = new Gson();

        for (int i = 0; i < 7; ++i) {
            CalendarHelper.rollToNextDay();
            for (int j = 0; j < 3; ++j) {
                WeatherData report;
                try {
                    report = dataFactory.basicWeatherDataInCity(City.values()[j]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                WeatherDataDTO dto = WeatherDataDTO.toDTO(report);
                toSend.add(dto);
            }
        }

        for (var dto : toSend) {
            String jsonData = gson.toJson(dto);
            var record = new ProducerRecord<String, Object>(topic, jsonData);
            producer.send(record);
            log.info("Send weather from producer {}", record);
        }

        toSend.clear();
    }
}
