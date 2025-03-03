package org.example.booking_api.model;

import jakarta.persistence.*;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long balance;

    @OneToOne
    private Users user;

}
