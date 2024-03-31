/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.service;

/**
 *
 * @author panha
 */
import com.school.main.dao.UserRepository;
import com.school.main.dto.UserRequest;
import com.school.main.exception.ResourceAlreadyExistsException;
import com.school.main.exception.ResourceNotFoundException;
import com.school.main.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
         if (userRepo.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User", user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User update(UserRequest newUser, UUID userId) {
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        if (!newUser.getEmail().equals(existingUser.getEmail())
                && userRepo.existsByEmail(newUser.getEmail())) {
            throw new ResourceAlreadyExistsException("User", newUser.getEmail());
        }

        existingUser.setEmail(newUser.getEmail());
        existingUser.setBirthdate(newUser.getBirthdate());
        if (!newUser.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }

        return userRepo.save(existingUser);
    }

    public List<User> find() {
        return userRepo.findAll();
    }

    public Optional<User> findById(UUID userId) {
        return userRepo.findById(userId);
    }

    public List<User> findByRole(String role) {
        return userRepo.findByRole(role);
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(emailOrUsername)
                .orElseGet(() -> {
                    return userRepo.findByUsername(emailOrUsername)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                });

        return user;
    }

    public boolean isEmailExist(String email) {
        return userRepo.existsByEmail(email);
    }

}
