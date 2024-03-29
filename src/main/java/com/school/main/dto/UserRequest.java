/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.dto;

/**
 *
 * @author panha
 */
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty(message = "{required.field}")
    private String firstName;
    private String lastName;
    private Date birthdate;

    @NotEmpty(message = "{required.field}")
    @Email(message = "Email iss not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String password;
}
