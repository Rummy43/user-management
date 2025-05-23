package com.tt.um.service;

import com.tt.um.model.User;
import com.tt.um.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
//            existingUser.setCreatedAt(user.getCreatedAt());
  //          existingUser.setUpdatedAt(user.getUpdatedAt());
            return userRepository.save(existingUser);
        }
        return null;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findAll().stream()
                .filter(user -> user.getName().equals(name))
                .toList();
    }

}
