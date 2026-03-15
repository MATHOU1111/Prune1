package com.example.api.User.Controller;

import com.example.api.User.Api.CreateUserRequest;
import com.example.api.User.Model.User;
import com.example.api.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Le backend fonctionne !";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(404).body("L'id indiqué est incorrecte");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.status(404).body("Erreur requête (getusersall)");
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User created = userService.createUser(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User updated = userService.updateUser(user);
        if (updated == null) {
            return ResponseEntity.status(404).body("L'id indiqué est incorrect");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User userTodelete = userService.DeleteUserById(id);
        if (userTodelete == null) {
            return ResponseEntity.status(404).body("L'id indiqué est incorrect");
        }
        return ResponseEntity.ok(userTodelete);
    }
}