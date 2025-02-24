package org.example.hotel_api.service.Imp;

import org.example.hotel_api.Exception.NotFoundBookingException;
import org.example.hotel_api.Exception.NotFoundHotelException;
import org.example.hotel_api.Exception.NotFoundRoomException;
import org.example.hotel_api.model.Booking;
import org.example.hotel_api.model.Hotel;
import org.example.hotel_api.model.Room;
import org.example.hotel_api.model.Status;
import org.example.hotel_api.repository.BookingRepository;
import org.example.hotel_api.repository.HotelRepository;
import org.example.hotel_api.repository.RoomRepository;
import org.example.hotel_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public String bookRoom(String hotelId, String userId,Booking booking){
        Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new NotFoundHotelException("Hotel not found"));
        Room room=roomRepository.findById(booking.getRoomId()).orElseThrow(()->new NotFoundRoomException("Room not found"));
        if(Status.Booked.equals(room.getStatus())){
            return "Room already booked";
        }
        bookingRepository.save(booking);
        room.setStatus(Status.Booked);
        roomRepository.save(room);
        return "Booked";

    }
    @Override
    public Booking getBookingById(int bookingId){
        Optional<Booking> booking=bookingRepository.findById(bookingId);
        if(!booking.isPresent()){
            throw new NotFoundBookingException("Booking not found");
        }
        return booking.get();
    }
    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }
    @Override
    public String cancelBooking(int bookingId){
        Booking booking=getBookingById(bookingId);
        Room room=roomRepository.findById(booking.getRoomId()).orElseThrow(()->new NotFoundRoomException("Room not found"));
        if(Status.Booked.equals(room.getStatus())){
            room.setStatus(Status.Booked);
            roomRepository.save(room);
        }
        bookingRepository.delete(booking);
        return "Booking cancelled";
    }

}
