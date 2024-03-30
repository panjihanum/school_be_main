/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.dto;

/**
 *
 * @author panha
 */
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class UserResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String email;
    private String role;
    private String username;

}
