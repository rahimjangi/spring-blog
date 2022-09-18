package com.raiseup.springblog.service;

import com.raiseup.springblog.dto.LoginRequest;
import com.raiseup.springblog.dto.RegisterRequest;
import com.raiseup.springblog.dto.RegisterResponse;
import com.raiseup.springblog.model.AppUser;
import com.raiseup.springblog.repository.UserRepository;
import com.raiseup.springblog.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public RegisterResponse signup(RegisterRequest registerRequest) {
        AppUser appUser = AppUser.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
        AppUser savedAppUser = userRepository.save(appUser);
        if(savedAppUser.getId()!=null){
            log.info("AppUser with id {} created ", savedAppUser.getId());
        }
        return RegisterResponse.builder()
                .username(savedAppUser.getUsername())
                .email(savedAppUser.getEmail())
                .build();
    }

    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        log.info("AuthService -> login-> authenticate {}",authenticate);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String generatedToken = jwtTokenProvider.generateToken(authenticate);
        log.info("AuthService -> login-> generatedToken {}",generatedToken);
        return generatedToken;
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
