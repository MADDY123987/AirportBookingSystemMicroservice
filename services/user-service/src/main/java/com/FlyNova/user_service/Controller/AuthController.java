package com.FlyNova.user_service.Controller;


import com.FlyNova.payload.dto.UserDTO;
import com.FlyNova.payload.request.LoginRequest;
import com.FlyNova.payload.response.AuthResponse;
import com.FlyNova.user_service.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid UserDTO userDTO) throws Exception {
        AuthResponse response=authService.signup(userDTO);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest req) throws Exception {
        AuthResponse response=authService.login(req.getEmail(),req.getPassword());
        return ResponseEntity.ok(response);
    }
}
