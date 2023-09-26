package ru.bondarev.transactions.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Value("${spring.kafka.topic-json.name}")
    private String topicName;

    @Bean
    public NewTopic jsonTopic(){
        return TopicBuilder.name(topicName)
                .build();
    }
}
