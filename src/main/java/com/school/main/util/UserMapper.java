/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.util;

import com.school.main.dto.UserRequest;
import com.school.main.dto.UserResponse;
import com.school.main.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toModel(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserRequest toRequest(User user) {
        return mapper.map(user, UserRequest.class);
    }

    public UserResponse toResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }

}
