package com.example.productsdelivery.services;

import com.example.productsdelivery.config.UserDets;
import com.example.productsdelivery.model.UserModel;
import com.example.productsdelivery.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepo.findByUsername(username);
        return user.map(UserDets::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
