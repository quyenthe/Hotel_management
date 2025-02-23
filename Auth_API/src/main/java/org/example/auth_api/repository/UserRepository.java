package org.example.auth_api.repository;

import org.example.auth_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    public Optional<Users> findByUsername(String username) throws UsernameNotFoundException;

}
