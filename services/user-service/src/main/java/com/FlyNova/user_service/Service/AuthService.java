package com.FlyNova.user_service.Service;

import com.FlyNova.payload.dto.UserDTO;
import com.FlyNova.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email,String Password);
    AuthResponse signup(UserDTO req) throws Exception;
}
