package com.example.services;

import lombok.RequiredArgsConstructor;
import com.example.configuration.security.JwtService;
import com.example.dtos.account.AuthResponseDTO;
import com.example.dtos.account.LoginDTO;
import com.example.dtos.account.RegisterDTO;
import com.example.entities.UserEntity;
import com.example.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDTO login(LoginDTO request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var isValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isValid) {
            throw new UsernameNotFoundException("User not found");
        }
        var jwtToken = jwtService.generateAccessToken(user);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDTO register(RegisterDTO request) {
        if (userRepository.findByEmail(request.getEmail()).orElse(null) != null) {
            throw new IllegalArgumentException("Email already registered");
        }

        UserEntity newUser = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .build();

        userRepository.save(newUser);

        var jwtToken = jwtService.generateAccessToken(newUser);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
