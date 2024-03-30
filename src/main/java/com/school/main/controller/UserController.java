/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.controller;

import com.school.main.constant.RoleConstant;
import com.school.main.dto.AuthenticationRequest;
import com.school.main.dto.AuthenticationResponse;
import com.school.main.dto.UserRequest;
import com.school.main.dto.UserResponse;
import com.school.main.model.User;
import com.school.main.service.AuthenticationService;
import com.school.main.service.UserService;
import com.school.main.util.UserMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserMapper mapper;
    
    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.find();
        var resp = users.stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(resp);
    }
    
    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        var user = mapper.toModel(request);
        user.setRole(RoleConstant.STUDENT.toString());
        user = userService.save(user);
        var resp = mapper.toResponse(user);
        return ResponseEntity.created(URI.create(user.getId().toString())).body(resp);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request.getEmail(), request.getPassword()));
    }
    
    @GetMapping("/user-detail")
    public ResponseEntity<UserResponse> getUserDetail(Authentication authentication) {
        var user = userService.loadUserByUsername((String) authentication.getPrincipal());
        var resp = mapper.toResponse((User) user);
        return ResponseEntity.ok(resp);
    }
    
}
