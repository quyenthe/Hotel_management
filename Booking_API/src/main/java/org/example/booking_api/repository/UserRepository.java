package org.example.booking_api.repository;

import org.example.booking_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users findByEmail(String email);
}
