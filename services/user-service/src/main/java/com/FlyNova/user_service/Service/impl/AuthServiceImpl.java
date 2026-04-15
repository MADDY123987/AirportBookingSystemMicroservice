package com.FlyNova.user_service.Service.impl;

import com.FlyNova.enums.UserRole;
import com.FlyNova.payload.dto.UserDTO;
import com.FlyNova.payload.response.AuthResponse;
import com.FlyNova.user_service.Mapper.UserMapper;
import com.FlyNova.user_service.Model.User;
import com.FlyNova.user_service.Repository.UserRepository;
import com.FlyNova.user_service.Service.AuthService;
import com.FlyNova.user_service.Service.CustomUserDetailService;
import com.FlyNova.user_service.config.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUserDetailService;

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
        Authentication authentication=new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),savedUser.getPassword()
                );
        String jwt=jwtProvider.generateToken(
                authentication,savedUser.getId()
        );
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDto(savedUser));
        authResponse.setTitle("Welcome"+savedUser.getFullName());
        authResponse.setMessage("Registered Succesfully");

        return authResponse;
    }

    /*
        1.Load user by Email
        2.Compare Password with Bcrypt
        3.Update LastLogin time
        4.Generate the JWT Token
        5.Return the Token and the user information
     */

    @Override
    public AuthResponse login(String email, String Password) throws Exception {
        Authentication authentication=authenticate(email, Password);
        User user=userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt=jwtProvider.generateToken(authentication,user.getId());
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDto(user));
        authResponse.setTitle("Welcome"+user.getFullName());
        authResponse.setMessage("Registered Succesfully");
        return authResponse;
    }
    private Authentication authenticate(String email,String password) throws Exception {
        UserDetails userDetails=customUserDetailService.loadUserByUsername(email);

        if(!passwordEncoder.matches(
                password,userDetails.getPassword()
        ))
        {
            throw new Exception("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,userDetails.
                getAuthorities());
    }
}
