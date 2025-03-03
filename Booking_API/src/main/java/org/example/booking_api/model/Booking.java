package org.example.booking_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Booking {
    @Id
    private String bookingId;
    private String hotelId;
    private String roomId;
    private LocalDate bookingDate;
    private LocalDate checkOutDate;
    private Integer amount;
    private PaymentStatus paymentStatus;

    private BookingStatus bookingStatus;
    @ManyToOne
    @JsonIgnore
    private Users user;
}
