package com.example.productsdelivery.controllers;

import com.example.productsdelivery.model.UserModel;
import com.example.productsdelivery.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<UserModel> getUserList() {
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public Optional<UserModel> getUser(@PathVariable String id) {
        return userRepo.findById(Integer.valueOf(id));
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        UserModel newUser = userRepo.save(user);
        return newUser;
    }

    @PutMapping("{id}")
    public UserModel updateUser(@PathVariable String id, @RequestBody UserModel user) {
        UserModel newUser = userRepo.findById(Integer.valueOf(id)).orElse(null);
        if(newUser == null) return null;
        if (user.getStatus() != null) {
            newUser.setStatus(user.getStatus());
        }
        if (user.getPhone() != null) {
            newUser.setPhone(user.getPhone());
        }
        if (user.getPassword() != null) {
            newUser.setPassword(user.getPassword());
        }
        if (user.getIdRole() != null) {
            newUser.setIdRole(user.getIdRole());
        }
        newUser.setId(Integer.valueOf(id));
        userRepo.save(newUser);
        return newUser;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        userRepo.deleteById(Integer.valueOf(id));
    }
}
