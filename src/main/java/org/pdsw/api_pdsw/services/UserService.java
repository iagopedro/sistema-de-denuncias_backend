package org.pdsw.api_pdsw.services;

import org.pdsw.api_pdsw.dto.UserRequestDTO;
import org.pdsw.api_pdsw.dto.UserResponseDTO;
import org.pdsw.api_pdsw.entities.User;
import org.pdsw.api_pdsw.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        List<User> users = this.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponseDTO getUserById(long id) {
        Optional<User> user = this.userRepository.findById(id);
        return new UserResponseDTO(user.get().getUsername(), user.get().getPassword());
    }

    public UserResponseDTO createUser(User userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());
        this.userRepository.save(user);
        return new UserResponseDTO(user.getUsername(), user.getPassword());
    }

    public UserResponseDTO updateUser(long id, UserRequestDTO userRequestDTO) {
        Optional<User> user = this.userRepository.findById(id);
        user.get().setUsername(userRequestDTO.getUsername());
        user.get().setPassword(userRequestDTO.getPassword());
        if (user.isPresent()) {
            User updatedUser = this.userRepository.save(user.get());
            return new UserResponseDTO(updatedUser.getUsername(), updatedUser.getPassword());
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
