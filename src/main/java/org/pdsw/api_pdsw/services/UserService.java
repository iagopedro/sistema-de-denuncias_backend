package org.pdsw.api_pdsw.services;

import org.pdsw.api_pdsw.dto.UserRequestDTO;
import org.pdsw.api_pdsw.dto.UserResponseDTO;
import org.pdsw.api_pdsw.entities.User;
import org.pdsw.api_pdsw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        //TODO: preciso pegar a lista de todos os usuários e verificar se "username" e "password" estão corretos
        List<User> users = this.userRepository.findAll();
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

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        Optional<User> user = this.userRepository.findById(userRequestDTO.getId());
        user.get().setUsername(userRequestDTO.getUsername());
        user.get().setPassword(userRequestDTO.getPassword());
        this.userRepository.save(user);
        return new UserResponseDTO(user.get().getUsername(), user.get().getPassword());
    }

}
