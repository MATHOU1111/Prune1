package com.example.api.user;

import org.bson.types.ObjectId;
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
        return userRepository.findById(new ObjectId(id)).orElse(null);
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name().trim());
        return userRepository.save(user);
    }
}
