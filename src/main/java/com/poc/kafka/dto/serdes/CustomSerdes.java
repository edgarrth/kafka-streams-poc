package com.poc.kafka.dto.serdes;

import com.poc.kafka.dto.User;
import com.poc.kafka.dto.User2;
import com.poc.kafka.dto.User3;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
public final class CustomSerdes {
    private CustomSerdes() {}
    public static Serde<User> User() {
        JsonSerializer<User> serializer = new JsonSerializer<>();
        JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<User2> User2() {
        JsonSerializer<User2> serializer = new JsonSerializer<>();
        JsonDeserializer<User2> deserializer = new JsonDeserializer<>(User2.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<User3> User3() {
        JsonSerializer<User3> serializer = new JsonSerializer<>();
        JsonDeserializer<User3> deserializer = new JsonDeserializer<>(User3.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

}