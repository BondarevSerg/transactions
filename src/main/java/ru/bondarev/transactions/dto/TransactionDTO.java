package ru.bondarev.transactions.dto;


import lombok.Builder;
import lombok.Data;


import java.io.Serializable;

/**
 * dto заказа со статусом оплаты
 */
@Data
@Builder
public class TransactionDTO implements Serializable{


    private Long userId;

    private Long orderId;

    private String userEmail;

    private boolean orderStatus;

}
