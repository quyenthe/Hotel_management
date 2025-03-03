package org.example.booking_api.repository;

import org.example.booking_api.model.Booking;
import org.example.booking_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    public List<Booking> findByHotelId(String hotelId);
    public List<Booking> findByUser(Users user);
}
