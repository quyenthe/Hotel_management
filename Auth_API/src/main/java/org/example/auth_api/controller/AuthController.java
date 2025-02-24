package org.example.auth_api.controller;

import org.example.auth_api.model.Users;
import org.example.auth_api.model.dto.LoginDTO;
import org.example.auth_api.model.dto.UserDTO;
import org.example.auth_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        if(userService.saveUser(userDTO)){
            return "success";
        }
        return "fail";

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        String token=userService.login(loginDTO);

        return ResponseEntity.ok(token);

    }

    @PostMapping("/get")
    public ResponseEntity<?> getUser() {
        List<Users> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}