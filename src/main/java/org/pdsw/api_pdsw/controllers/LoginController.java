package org.pdsw.api_pdsw.controllers;

import java.util.HashMap;
import java.util.Map;

import org.pdsw.api_pdsw.dto.UserRequestDTO;
import org.pdsw.api_pdsw.security.JwtUtil;
import org.pdsw.api_pdsw.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public LoginController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userRequestDTO) {
        if (userService.authenticate(userRequestDTO.getEmail(), userRequestDTO.getPassword())) {
            String token = jwtUtil.generateToken(userRequestDTO.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        Map<String, String> error = new HashMap<>();
        error.put("error", "User not found. Invalid credentials!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
