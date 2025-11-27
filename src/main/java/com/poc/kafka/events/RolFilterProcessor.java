package com.poc.kafka.events;

import com.poc.kafka.dto.User;
import com.poc.kafka.dto.User2;
import com.poc.kafka.dto.serdes.CustomMapper;
import com.poc.kafka.dto.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@EnableKafkaStreams
public class RolFilterProcessor {
   private static final Serde<String> STRING_SERDE = Serdes.String();
    @Value("${messaging-config.topics[0].name}")
    private String inputTopic;
    @Value("${messaging-config.topics[1].name}")
    private String outputTopic;
    @Value("${messaging-config.topics[2].name}")
    private String outputTopicDevs;
    @Value("${messaging-config.topics[3].name}")
    private String outputTopicLTs;
    private CustomMapper mapper = Mappers.getMapper(CustomMapper.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
/*
        streamsBuilder
                .stream(inputTopic, Consumed.with(STRING_SERDE, CustomSerdes.User()))
                .filter((key, value) -> value.getRol().equals("Arq"))
                .to(outputTopic, Produced.with(STRING_SERDE, CustomSerdes.User()));

        streamsBuilder
                .stream(inputTopic, Consumed.with(STRING_SERDE, CustomSerdes.User()))
                .split()
                .branch(
                        (key, value) -> value.getRol().equals("Dev"),
                        Branched.withConsumer(devs -> devs.to(outputTopicDevs)))
                .branch(
                        (key, value) -> value.getRol().equals("LT"),
                        Branched.withConsumer(devs -> devs.to(outputTopicLTs)))
                .defaultBranch();

     StreamsBuilder builder = new StreamsBuilder();

     KStream<String, User> stream1 = builder.stream(inputTopic, Consumed.with(Serdes.String(), CustomSerdes.User()));
     KStream<String, User> stream2 = builder.stream(inputTopic, Consumed.with(Serdes.String(), CustomSerdes.User()));
     KStream<String, User> stream3 = builder.stream(inputTopic, Consumed.with(Serdes.String(), CustomSerdes.User()));

     KStream<String, User> mergedStream = stream1.merge(stream2).merge(stream3);

     mergedStream
             //.stream(inputTopic, Consumed.with(STRING_SERDE, CustomSerdes.User()))
             .split()
             .branch(
                     (key, value) -> value.getRol().equals("Dev"),
                     Branched.withConsumer(devs -> devs.mapValues((k, v) -> mapper.toUser2(v))
                             .to(outputTopicDevs, Produced.with(STRING_SERDE, CustomSerdes.User2()))))
             .branch(
                     (key, value) -> value.getRol().equals("LT"),
                     Branched.withConsumer(devs -> devs.mapValues((k, v) -> mapper.toUser3(v))
                             .to(outputTopicLTs, Produced.with(STRING_SERDE, CustomSerdes.User3()))))
             .defaultBranch();
*/
     List<String> inputTopics = Arrays.asList("input-topic-1", "input-topic-2", "input-topic-3", "input-topic-4", "input-topic-5");

     streamsBuilder
             .stream(inputTopics, Consumed.with(STRING_SERDE, CustomSerdes.User()))
             .split()
             .branch(
                     (key, value) -> value.getRol().equals("Dev"),
                     Branched.withConsumer(devs -> devs.mapValues((k, v) -> mapper.toUser2(v))
                             .to(outputTopicDevs, Produced.with(STRING_SERDE, CustomSerdes.User2()))))
             .branch(
                     (key, value) -> value.getRol().equals("LT"),
                     Branched.withConsumer(devs -> devs.mapValues((k, v) -> mapper.toUser3(v))
                             .to(outputTopicLTs, Produced.with(STRING_SERDE, CustomSerdes.User3()))))
             .defaultBranch();
    }

}
