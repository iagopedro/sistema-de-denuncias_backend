package org.pdsw.api_pdsw.controllers;

import org.pdsw.api_pdsw.dto.UserRequestDTO;
import org.pdsw.api_pdsw.dto.UserResponseDTO;
import org.pdsw.api_pdsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserRequestDTO userRequestDTO) {
        if (userService.authenticate(userRequestDTO.getUsername(), userRequestDTO.getPassword())) {
            return ResponseEntity.ok().body("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized. Invalid credentials!");
    }

}
