package com.weather_project.consumer.service.messaging.consumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
public class WeatherConsumerTest {
    @Value("${topic.weather-report}")
    private String topic;
    @Autowired
    private WeatherConsumer weatherConsumer;

    private Properties getProducerProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put("acks", "1");

        return properties;
    }

    @Test
    public void shouldConsumeMessage() {
        String msg = "Message!";

        weatherConsumer.subscribe();

        try (KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<>(getProducerProperties())) {
            kafkaProducer.send(new ProducerRecord<>(topic, msg));
        } catch (Exception e) {
            e.printStackTrace();
        }

        var reports = weatherConsumer.poll();
        assertTrue(reports.count() > 0);
        Object val = reports.iterator().next().value();
        assertEquals(msg, val);
        System.out.println(val);
    }
}
