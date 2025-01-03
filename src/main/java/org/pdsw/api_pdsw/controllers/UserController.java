package org.pdsw.api_pdsw.controllers;

import org.pdsw.api_pdsw.dto.UserRequestDTO;
import org.pdsw.api_pdsw.dto.UserResponseDTO;
import org.pdsw.api_pdsw.entities.User;
import org.pdsw.api_pdsw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUser(String username) {
        UserResponseDTO userResponseDTO = this.userService.getUserByUsername(username);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = new User();
        newUser.setUsername(userRequestDTO.getUsername());
        newUser.setPassword(userRequestDTO.getPassword());
        this.userService.createUser(newUser);
        return ResponseEntity.ok("User created");
    }
}
