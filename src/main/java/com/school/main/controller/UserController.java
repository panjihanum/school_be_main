/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.controller;

import com.school.main.constant.RoleConstant;
import com.school.main.dto.AuthenticationRequest;
import com.school.main.dto.AuthenticationResponse;
import com.school.main.dto.UserProfileResponse;
import com.school.main.dto.UserRequest;
import com.school.main.dto.UserResponse;
import com.school.main.model.User;
import com.school.main.service.AuthenticationService;
import com.school.main.service.UserService;
import com.school.main.util.UserMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/list-students")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<UserResponse>> getListStudents() {
        List<User> users = userService.findByRole(RoleConstant.STUDENT.toString());
        List<UserResponse> resp = new ArrayList<>();
        users.forEach(user -> {
            resp.add(mapper.toResponse(user));
        });
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/list-teachers")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<UserResponse>> getListTeachers() {
        List<User> users = userService.findByRole(RoleConstant.TEACHER.toString());
        List<UserResponse> resp = new ArrayList<>();
        users.forEach(user -> {
            resp.add(mapper.toResponse(user));
        });
        return ResponseEntity.ok(resp);
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

    @GetMapping("/user-profile/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfileById(@PathVariable UUID userId) {
        var user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        var resp = mapper.toProfileResponse(user.get());
        return ResponseEntity.ok(resp);
    }

}
