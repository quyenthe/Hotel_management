package org.example.auth_api.service.Imp;

import org.example.auth_api.Exception.UserExistedException;
import org.example.auth_api.model.Role;
import org.example.auth_api.model.Users;
import org.example.auth_api.model.dto.LoginDTO;
import org.example.auth_api.model.dto.UserDTO;
import org.example.auth_api.repository.RoleRepository;
import org.example.auth_api.repository.UserRepository;
import org.example.auth_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Override
    public String login(LoginDTO loginDTO) {
        Optional<Users> user = userRepository.findByUsername(loginDTO.getUsername());
        if (!user.isPresent()) {
            System.out.println("User not found");
        }
        Users users = user.get();
        if(!passwordEncoder.matches(loginDTO.getPassword(), users.getPassword())) {
            System.out.println("Wrong password");
        }


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtTokenService.generateToken(loginDTO.getUsername());
        }
        else {
            return "Error generating token";
        }
    }
    @Override

    public boolean saveUser(UserDTO userDTO) throws UserExistedException {
       Optional<Users> users = userRepository.findByUsername(userDTO.getUsername());

        // Kiểm tra nếu User đã tồn tại
        if (users.isPresent()) {
            // Nếu đã tồn tại, ném Exception
            throw new UserExistedException("User already existed");
        }
        Role role;

        Optional<Role> roleOptional = roleRepository.findByRoleType("USER");
        if (roleOptional.isPresent()) {
            role = roleOptional.get();

        } else {
            role = new Role();
            role.setRoleType("USER");
            roleRepository.save(role);
        }

        Users newUser = new Users();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Nên mã hóa mật khẩu trước khi lưu
        newUser.setRole(role);
        userRepository.save(newUser);

        return true;
    }

}
