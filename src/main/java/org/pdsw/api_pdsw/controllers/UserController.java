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

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = new User();
        newUser.setUsername(userRequestDTO.getUsername());
        newUser.setPassword(userRequestDTO.getPassword());
        this.userService.createUser(newUser);
        return ResponseEntity.ok("User created");
    }

    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam long id) {
        UserResponseDTO userResponseDTO = this.userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = new User();
        newUser.setUsername(userRequestDTO.getUsername());
        newUser.setPassword(userRequestDTO.getPassword());
        userService.updateUser(userRequestDTO);
        return ResponseEntity.ok("User updated");
    }

}
