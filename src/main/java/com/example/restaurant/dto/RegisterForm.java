package com.example.restaurant.dto;
import lombok.*;

@Data
public class RegisterForm {
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
}
