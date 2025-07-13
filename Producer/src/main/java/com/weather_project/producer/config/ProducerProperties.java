package com.weather_project.producer.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerProperties {
    public static final String BOOTSTRAP_SERVERS = "localhost:29092";
    public static final String KEY_SERIALIZER = StringSerializer.class.getName();
    public static final String VALUE_SERIALIZER = StringSerializer.class.getName();
    public static final String acks = String.valueOf(1);
}
