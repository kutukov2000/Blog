package com.example.services;

import lombok.RequiredArgsConstructor;
import com.example.configuration.security.JwtService;
import com.example.dtos.account.AuthResponseDTO;
import com.example.dtos.account.LoginDTO;
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
}
