package ru.bondarev.transactions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * dto заказа
 */
@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private Long userId;

    private Long orderId;

    private String userEmail;


}
