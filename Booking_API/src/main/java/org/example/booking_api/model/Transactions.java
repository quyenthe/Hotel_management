package org.example.booking_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;

import java.time.LocalDateTime;

public class Transactions {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int transactionId;

    //    @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime transactionDate;


    private Long amount;


    private Long CurrentBalance;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
