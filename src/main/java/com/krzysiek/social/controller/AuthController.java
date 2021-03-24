package com.krzysiek.social.controller;

import com.krzysiek.social.dto.AuthenticationResponse;
import com.krzysiek.social.dto.RegisterRequest;
import com.krzysiek.social.dto.LoginRequest;
import com.krzysiek.social.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {

        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration successful.",
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

        return authService.login(loginRequest);

    }

    @GetMapping("account-verification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {

        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully!",HttpStatus.OK);
    }


}
