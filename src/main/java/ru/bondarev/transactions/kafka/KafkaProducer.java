package ru.bondarev.transactions.kafka;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.bondarev.transactions.dto.TransactionDTO;


@Service
public class KafkaProducer {


    @Value("${spring.kafka.topic-json.name}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private   KafkaTemplate<String, TransactionDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, TransactionDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TransactionDTO transactionDTO){

        LOGGER.info(String.format("Message sent -> %s", transactionDTO.toString()));

        Message<TransactionDTO> message = MessageBuilder
                .withPayload(transactionDTO)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
