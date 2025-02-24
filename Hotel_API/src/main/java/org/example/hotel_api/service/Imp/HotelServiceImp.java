package org.example.hotel_api.service.Imp;

import org.example.hotel_api.Exception.NotFoundHotelException;
import org.example.hotel_api.model.Hotel;
import org.example.hotel_api.model.Room;
import org.example.hotel_api.repository.HotelRepository;
import org.example.hotel_api.repository.RoomRepository;
import org.example.hotel_api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImp implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Hotel addHotel(Hotel hotel) {
        String Id= UUID.randomUUID().toString();
        hotel.setId(Id);
        return hotelRepository.save(hotel);
    }
    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new NotFoundHotelException("hotel not found"));
    }
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    @Override
    public Hotel deleteHotelById(String hotelId){
        Hotel hotel=getHotelById(hotelId);
        hotelRepository.delete(hotel);
        return hotel;
    }
    @Override
    public Room addRoom( Room room){
        Hotel hotel=getHotelById(room.getHotel().getId());
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

}
