/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.dto;

/**
 *
 * @author panha
 */
import lombok.Data;

@Data
public class UserResponse {

    private Integer id;

    private String name;

    private String email;

    private String role;

}
