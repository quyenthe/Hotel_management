package org.example.booking_api.service.Imp;

import org.example.booking_api.exception.BookingException;
import org.example.booking_api.exception.HotelException;
import org.example.booking_api.exception.UserException;
import org.example.booking_api.model.*;
import org.example.booking_api.repository.BookingRepository;
import org.example.booking_api.repository.UserRepository;
import org.example.booking_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Booking bookRoom(int userId, String hotelId){
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
        Hotel hotel= restTemplate.getForObject("https://Hotel_API/hotel/getHotelById?hotelId="+hotelId, Hotel.class);
        if(hotel==null){
            throw new HotelException("Hotel not found");
        }
        List<Room> rooms=hotel.getRooms();

        Room room= rooms.stream().filter(f-> Status.AVAILABLE.equals(f.getStatus())).findFirst().orElseThrow(() -> new NotFoundException("Room not found"));

        Booking booking = Booking.builder()
                .user(user)
                .hotelId(hotelId)
                .roomId(room.getId())
                .bookingId("Booking - "+ System.currentTimeMillis())
                .amount(room.getRoomPrice())
                .paymentStatus(PaymentStatus.UNPAID)
                .bookingDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(1))
                .bookingStatus(BookingStatus.BOOKED)
                .build();

        String  isBookingAdded=restTemplate.postForObject("https://Hotel_API/hotel/addBooking/"+hotelId+"/"+userId, booking, String.class);

        if(!"Booked".equals(isBookingAdded)){
            throw new BookingException("Booking failed");
        }

        return booking;
    }

    @Override
    public String cancelBooking(String bookingId){
        Booking booking=bookingRepository.findById(bookingId).orElseThrow(()->new BookingException("Booking not found"));
        String cancelBooking = restTemplate.getForObject("http://Hotel_API/hotel/cancelBooking/"+bookingId,String.class);
        if(!"Booking cancelled".equals(cancelBooking)){
            throw new BookingException("cancelBooking failed");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return cancelBooking;

    }
    @Override
    public List<Booking> getAllBookings(int userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
        return bookingRepository.findByUser(user);

    }

    @Override
    public Booking getBookingById(String bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow( () -> new BookingException("Booking not found with id: " + bookingId + ""));
    }

    @Override
    public List<Booking> getAllBookingsByHotelId(String hotelId) {
        return bookingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Hotel> getHotelByLocation(String location) {
        List<Hotel> hotels = restTemplate.getForObject("http://Hotel_API/hotel/getHotelByLocation/"+location,List.class);
        if (hotels == null) {
            throw new HotelException("Hotel not found");
        }
        return hotels;
    }

    @Override
    public List<Hotel> getHotelByName(String name) {
        List<Hotel> hotels = restTemplate.getForObject("http://Hotel_API/hotel/getHotelByName/"+name,List.class);

        if (hotels == null) {
            throw new HotelException("Hotel not found");
        }
        return hotels;
    }
    @Override
    public List<Hotel> availableHotels(String hotelId) {
//        @GetMapping("/{hotelId}/available")
        List<Hotel> hotels = restTemplate.getForObject("http://HOTELS-SERVICE/hotels/"+hotelId+"/available",List.class);

        if (hotels == null) {
            throw new HotelException("Hotel not found");
        }
        return hotels;
    }
}
