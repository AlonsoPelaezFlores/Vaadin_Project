package com.example.vaadin_project.backend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreateUserDTO(
        @NotEmpty(message = "The name is required")
        String name,
        @NotEmpty(message = "The lastname is required")
        String lastname,
        @NotEmpty(message = "The name is required")
        @Email(message = "The email address is invalid")
        String email,
        @NotEmpty(message = "The password is required")
        String password,
        @NotEmpty(message = "The birthday is required")
        @Past(message = "Date of birth must be before today")
        LocalDate birthday
) {
}
