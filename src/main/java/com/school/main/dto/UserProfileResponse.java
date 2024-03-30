/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.dto;

/**
 *
 * @author panha
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserProfileResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

}
