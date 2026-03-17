package com.example.api.User.Service;

import com.example.api.User.Api.CreateUserRequest;
import com.example.api.User.Model.User;
import com.example.api.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new Error("User not found with username " + username);
        }
        return user;
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.name() == null ? null : request.name().trim());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            return null;
        }
        return userRepository.save(user);
    }

    public User deleteUserById(String id) {
        User user = getUserById(id);
        if (user == null) {
            return null;
        }
        userRepository.delete(user);
        return user;
    }
}
