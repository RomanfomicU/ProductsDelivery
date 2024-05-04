package com.example.productsdelivery.controllers;

import com.example.productsdelivery.config.UserCredentials;
import com.example.productsdelivery.model.UserModel;
import com.example.productsdelivery.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('3')")
    public List<UserModel> getUserList() {
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('3') || hasAuthority('2') || hasAuthority('1')")
    public Optional<UserModel> getUser(@PathVariable String id) {
        return userRepo.findById(Integer.valueOf(id));
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserCredentials userCredentials){
        UserModel user = userRepo.findByUsername(userCredentials.getLogin()).orElse(null);
        if (user != null && passwordEncoder.matches(userCredentials.getPassword(), user.getPassword())) {
            return user.getId().toString();
        }
        return "False";
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel newUser = userRepo.save(user);
        return newUser;
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('3') || hasAuthority('2') || hasAuthority('1')")
    public UserModel updateUser(@PathVariable String id, @RequestBody UserModel user) {
        UserModel newUser = userRepo.findById(Integer.valueOf(id)).orElse(null);
        if(newUser == null) return null;
        if (user.getUsername() != null) {
            newUser.setUsername(user.getUsername());
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
    @PreAuthorize("hasAuthority('3')")
    public void deleteUser(@PathVariable String id) {
        userRepo.deleteById(Integer.valueOf(id));
    }

}
