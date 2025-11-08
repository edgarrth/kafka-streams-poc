package com.poc.kafka.dto.serdes;

import com.poc.kafka.dto.User;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
public final class CustomSerdes {
    private CustomSerdes() {}
    public static Serde<User> User() {
        JsonSerializer<User> serializer = new JsonSerializer<>();
        JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}