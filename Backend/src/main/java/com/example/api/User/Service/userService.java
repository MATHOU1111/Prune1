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

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(!user || user == null){
            throw new UsernameNotFoundException("User not found with username " + username);
        }
        else{
            return new
        }

    }

    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.username().trim());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            return null;
        }
        return userRepository.save(user);
    }

    public User DeleteUserById(String id) {
        User user = getUserById(id);
        if (user == null) {
            return null;
        }
        userRepository.delete(user);
        return user;
    }
}
