package com.school.main;

import com.school.main.constant.RoleConstant;
import com.school.main.model.User;
import com.school.main.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            if (!userService.isEmailExist("admin@school.com")) {
                userService.save(new User("Admin", "Sekolah", "admin", "admin@school.com", "123456", RoleConstant.ADMIN.toString()));
            }
            if (!userService.isEmailExist("sari.lukito@school.com")) {
                userService.save(new User("Sari", "Lukito", "teacher_sari", "sari.lukito@school.com", "123456", RoleConstant.TEACHER.toString()));
            }
            if (!userService.isEmailExist("teacher.andre@school.com")) {
                userService.save(new User("Andre", "Siregar", "teacher_andre", "teacher.andre@school.com", "123456", RoleConstant.TEACHER.toString()));
            }
            if (!userService.isEmailExist("student.sabdo@school.com")) {
                userService.save(new User("Muhammad", "Sabdo", "muhammad_sabdo", "student.sabdo@school.com", "123456", RoleConstant.STUDENT.toString()));
            }
            if (!userService.isEmailExist("student.panji@school.com")) {
                userService.save(new User("Student", "Panji", "student_panji", "student.panji@school.com", "123456", RoleConstant.STUDENT.toString()));
            }
            if (!userService.isEmailExist("anugerah.ramadhan@school.com")) {
                userService.save(new User("Anugerah", "Ramadhan", "student_adam", "anugerah.ramadhan@school.com", "123456", RoleConstant.STUDENT.toString()));
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper getModelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        return mapper;
    }
}
