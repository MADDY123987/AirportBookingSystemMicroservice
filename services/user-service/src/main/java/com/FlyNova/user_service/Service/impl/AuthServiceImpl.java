package com.FlyNova.user_service.Service.impl;

import com.FlyNova.enums.UserRole;
import com.FlyNova.payload.dto.UserDTO;
import com.FlyNova.payload.response.AuthResponse;
import com.FlyNova.user_service.Model.User;
import com.FlyNova.user_service.Repository.UserRepository;
import com.FlyNova.user_service.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthService authService;
    /*
    1.check if the email Already Exists
    2.Encode password using Bcrypt
    3.save user in the database
    4.generate the jwt token
    5.return the token and user information
    */
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser=userRepository.findByEmail(req.getEmail());
        if(existingUser!=null)
        {
            throw new Exception("Email Already Registered");
        }
        if(req.getRole()== UserRole.ROLE_SYSTEM_ADMIN)
        {
            throw new Exception("You cannot signup as System Admin!");
        }
        User newUser=User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .fullName(req.getFullName())
                .lastLogin(req.getLastLogin())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        User savedUser=userRepository.save(newUser);
        return  null;
    }

    @Override
    public AuthResponse login(String email, String Password) {
        return null;
    }


}
