package com.FlyNova.user_service.Service;


import com.FlyNova.payload.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByEmail(String Email) throws Exception;
    UserDTO getUserById(Long id) throws Exception;
    List<UserDTO> getAllUsers();
}
