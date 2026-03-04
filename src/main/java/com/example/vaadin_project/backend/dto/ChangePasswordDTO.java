package com.example.vaadin_project.backend.dto;


public record ChangePasswordDTO(
    String password,
    String newPassword,
    String confirmPassword
){}
