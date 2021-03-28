package com.krzysiek.social.controller;

import com.krzysiek.social.dto.AuthenticationResponse;
import com.krzysiek.social.dto.LoginRequest;
import com.krzysiek.social.dto.RefreshTokenRequest;
import com.krzysiek.social.dto.RegisterRequest;
import com.krzysiek.social.service.AuthService;
import com.krzysiek.social.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

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

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token deleted successfully!!");
    }

}
