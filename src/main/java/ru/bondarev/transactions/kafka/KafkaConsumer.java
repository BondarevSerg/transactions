package ru.bondarev.transactions.kafka;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.bondarev.transactions.dto.OrderDTO;
import ru.bondarev.transactions.dto.TransactionDTO;

/**
 * слушаем топик  (пример: статус заказа оплачен), отправляем продюсеру
 */
@Service
@AllArgsConstructor
public class KafkaConsumer {
     final private KafkaProducer kafkaProducer;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "order_details", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderDTO orderDTO){

        var transaction=TransactionDTO.builder()
                .userId(orderDTO.getUserId())
                .orderId(orderDTO.getOrderId())
                .userEmail(orderDTO.getUserEmail())
                .orderStatus(true)
                .build();

        kafkaProducer.sendMessage(transaction);
        System.out.println(transaction);
        LOGGER.info(String.format("Json message recieved -> %s", orderDTO));
    }
}
