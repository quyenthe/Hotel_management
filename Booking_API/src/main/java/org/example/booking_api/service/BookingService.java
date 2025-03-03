package org.example.booking_api.service;

import org.example.booking_api.model.Booking;
import org.example.booking_api.model.Hotel;

import java.util.List;

public interface BookingService {
    public Booking bookRoom(int userId, int hotelId);

    public String cancelBooking(String bookingId);

    public List<Booking> getAllBookings(String userId);

    public Booking getBookingById(String bookingId);
    public List<Booking> getAllBookingsByHotelId(String hotelId);

    public List<Hotel> getHotelByLocation(String location);

    public List<Hotel> getHotelByName(String name);

    public List<Hotel> availableHotels(String hotelId);

    public Booking completeBooking(String bookingId);
}
