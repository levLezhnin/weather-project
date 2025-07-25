package com.weather_project.consumer.config;

import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerProperties {
    public static final String BOOTSTRAP_SERVERS = "localhost:29092";
    public static final String GROUP_ID = "receiver-1";
    public static final String AUTO_OFFSET_RESET = "earliest";
    public static final String KEY_DESERIALIZER = StringDeserializer.class.getName();
    public static final String VALUE_DESERIALIZER = StringDeserializer.class.getName();
}
