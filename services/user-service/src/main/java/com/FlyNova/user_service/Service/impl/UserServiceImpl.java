package com.FlyNova.user_service.Service.impl;

import com.FlyNova.payload.dto.UserDTO;
import com.FlyNova.user_service.Mapper.UserMapper;
import com.FlyNova.user_service.Model.User;
import com.FlyNova.user_service.Repository.UserRepository;
import com.FlyNova.user_service.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    @Override
    public UserDTO getUserByEmail(String Email) throws Exception {
        User user=userRepository.findByEmail(Email);
        if(user==null)
        {
            throw new Exception("User Not found With Email");
        }
        return UserMapper.toDto(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        User user=userRepository.findById(id).orElseThrow(
                ()->new Exception("User not Found with Id"+id)
        );
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User>users= userRepository.findAll();
        return UserMapper.userDTOS(users);
    }
}
