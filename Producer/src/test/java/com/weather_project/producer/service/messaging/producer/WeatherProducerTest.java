package com.weather_project.producer.service.messaging.producer;

import com.weather_project.producer.service.dto.WeatherDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Slf4j
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
public class WeatherProducerTest {

    @Autowired
    private WeatherProducer weatherProducer;

    private Properties getConsumerProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

    @Test
    public void shouldSendWeatherReport() {
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(getConsumerProperties());
        consumer.subscribe(List.of("test-topic"));

        weatherProducer.sendReport();

        ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(15));
        consumer.close();

        Assertions.assertTrue(records.count() > 0);
        for (var recs : records) {
            log.info("Parsed DTO: {}", WeatherDataDTO.fromJson((String) recs.value()));
        }
    }
}
