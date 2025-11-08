package com.poc.kafka.events;

import com.poc.kafka.dto.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@Component
@EnableKafkaStreams
public class RolFilterProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    @Value("${spring.kafka.streams.topics.input-topic}")
    private String inputTopic;

    @Value("${spring.kafka.streams.topics.output-topic}")
    private String outputTopic;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {

        streamsBuilder
                .stream(inputTopic, Consumed.with(STRING_SERDE, CustomSerdes.User()))
                .filter((key, value) -> value.getRol().equals("Arq"))
                .to(outputTopic, Produced.with(STRING_SERDE, CustomSerdes.User()));
    }

}
