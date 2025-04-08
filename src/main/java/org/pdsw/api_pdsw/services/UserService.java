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
    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public boolean authenticate(String email, String password) {
        List<User> users = this.getAllUsers();
        if (users.isEmpty()) {
            return false;
        }
        
        for (User user : users) {
            var hashedPassword = user.getPassword();
            if (user.getEmail().equals(email) && passwordService.verifyPassword(password, hashedPassword)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public UserResponseDTO getUserById(long id) {
        Optional<User> user = this.userRepository.findById(id);
        return new UserResponseDTO(user.get().getName(), user.get().getEmail(), user.get().getPassword());
    }

    public UserResponseDTO createUser(User userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        String hashedPassword = this.passwordService.hashPassword(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);
        this.userRepository.save(user);
        return new UserResponseDTO(user.getName(), user.getEmail(), user.getPassword());
    }

    public UserResponseDTO updateUser(long id, UserRequestDTO userRequestDTO) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(userRequestDTO.getName());
            user.get().setEmail(userRequestDTO.getEmail());
            String hashedPassword = this.passwordService.hashPassword(userRequestDTO.getPassword());
            user.get().setPassword(hashedPassword);
            User updatedUser = this.userRepository.save(user.get());
            return new UserResponseDTO(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPassword());
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
