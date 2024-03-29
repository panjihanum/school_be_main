/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.school.main.dto;

/**
 *
 * @author panha
 */
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty(message = "{required.field}")
    private String name;

    @NotEmpty(message = "{required.field}")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String password;

    @NotEmpty(message = "{required.field}")
    private String role;

}
