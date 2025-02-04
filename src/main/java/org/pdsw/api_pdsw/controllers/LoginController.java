package org.pdsw.api_pdsw.controllers;

import org.pdsw.api_pdsw.dto.UserRequestDTO;
import org.pdsw.api_pdsw.services.PasswordService;
import org.pdsw.api_pdsw.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserRequestDTO userRequestDTO) {
        if (userService.authenticate(userRequestDTO.getUsername(), userRequestDTO.getPassword())) {
            return ResponseEntity.ok().body("Login successful");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. Invalid credentials!");
    }

}
