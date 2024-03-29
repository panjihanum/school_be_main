/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.service;

/**
 *
 * @author panha
 */
import com.school.main.constant.RoleConstant;
import com.school.main.dao.UserRepository;
import com.school.main.exception.ResourceAlreadyExistsException;
import com.school.main.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setRole(RoleConstant.STUDENT.toString());
        return userRepo.save(user);
    }

    public List<User> find() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean isEmailExist(String email) {
        return userRepo.existsByEmail(email);
    }

}
