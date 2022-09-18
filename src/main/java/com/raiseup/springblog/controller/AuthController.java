package com.raiseup.springblog.controller;

import com.raiseup.springblog.dto.LoginRequest;
import com.raiseup.springblog.dto.RegisterRequest;
import com.raiseup.springblog.dto.RegisterResponse;
import com.raiseup.springblog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private  AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<RegisterResponse> signup(@RequestBody RegisterRequest registerRequest){

        RegisterResponse registerResponse = authService.signup(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
