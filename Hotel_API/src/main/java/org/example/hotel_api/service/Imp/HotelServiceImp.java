package org.example.hotel_api.service.Imp;

import org.example.hotel_api.Exception.NotFoundHotelException;
import org.example.hotel_api.Exception.NotFoundRoomException;
import org.example.hotel_api.model.Hotel;
import org.example.hotel_api.model.Room;
import org.example.hotel_api.model.Status;
import org.example.hotel_api.repository.HotelRepository;
import org.example.hotel_api.repository.RoomRepository;
import org.example.hotel_api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public Room getRoomById(String roomId){
        Optional<Room> room=roomRepository.findById(roomId);
        if(!room.isPresent()){
            throw new NotFoundRoomException("room not found");
        }
        return room.get();
    }
    @Override
    public List<Room> getAllAvailableRoom(String hotelId){
        List<Room> rooms=roomRepository.findAll().stream()
                .filter(room -> hotelId.equals(room.getHotel().getId()))
                .filter(room -> Status.Available.equals(room.getStatus()))
                .collect(Collectors.toList());
        return rooms;
    }
    @Override
    public List<Room> getAllBookedRoom(String hotelId){
        List<Room> rooms=roomRepository.findAll().stream()
                .filter(room -> hotelId.equals(room.getHotel().getId()))
                .filter(room -> Status.Booked.equals(room.getStatus()))
                .collect(Collectors.toList());
        return rooms;
    }
    @Override
    public List<Room> getAllRooms(String hotelId){
        List<Room> rooms=roomRepository.findAll().stream()
                .filter(room -> hotelId.equals(room.getHotel().getId()))
                .collect(Collectors.toList());
        return rooms;
    }
    @Override
    public Room deleteRoomById(String roomId){
        Room room=getRoomById(roomId);
        roomRepository.delete(room);
        return room;
    }
    @Override
    public List<Hotel> getHotelByLocation(String location){
        Optional<List<Hotel>> hotel=hotelRepository.findByLocation(location);
        if(!hotel.isPresent()){
            throw new NotFoundHotelException("hotel not found");
        }
        return hotel.get();
    }
    @Override
    public Hotel getHotelByName(String name){
        Optional<Hotel> hotel=hotelRepository.findByName(name);
        if (!hotel.isPresent()){
            throw new NotFoundHotelException("hotel not found");
        }
        return hotel.get();
    }

}
