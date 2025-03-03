package org.example.booking_api.service.Imp;

import org.example.booking_api.exception.UserException;
import org.example.booking_api.model.Booking;
import org.example.booking_api.model.Hotel;
import org.example.booking_api.model.Users;
import org.example.booking_api.repository.BookingRepository;
import org.example.booking_api.repository.UserRepository;
import org.example.booking_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Booking bookRoom(int userId, int hotelId){
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
        Hotel hotel= restTemplate.getForObject()
    }
}
