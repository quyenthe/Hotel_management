package org.example.auth_api.service;

import org.example.auth_api.Exception.UserExistedException;
import org.example.auth_api.model.Users;
import org.example.auth_api.model.dto.LoginDTO;
import org.example.auth_api.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    public boolean saveUser(UserDTO user) throws UserExistedException;
    public String login(LoginDTO login);
    public List<Users> getAllUsers();
}
