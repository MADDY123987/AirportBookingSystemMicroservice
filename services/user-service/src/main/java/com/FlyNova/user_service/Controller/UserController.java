package com.FlyNova.user_service.Controller;


import com.FlyNova.payload.dto.UserDTO;
import com.FlyNova.user_service.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(
            @RequestHeader("X-User-Email") String Email
    ) throws Exception {
        UserDTO user=userService.getUserByEmail(Email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable Long userId
    ) throws Exception {
        UserDTO user=userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
