/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotEmpty(message = "{required.field}")
    @Email(message = "{invalid.field}")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String password;

}
