package com.example.controllers;

import lombok.RequiredArgsConstructor;
import com.example.dtos.account.AuthResponseDTO;
import com.example.dtos.account.LoginDTO;
import com.example.dtos.account.RegisterDTO;
import com.example.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        try {
            var auth = accountService.login(dto);
            return ResponseEntity.ok(auth);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO dto) {
        try {
            var auth = accountService.register(dto);
            return ResponseEntity.ok(auth);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
