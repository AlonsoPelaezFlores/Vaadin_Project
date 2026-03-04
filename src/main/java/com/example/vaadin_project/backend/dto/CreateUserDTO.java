package com.example.vaadin_project.backend.dto;

import com.example.vaadin_project.backend.entity.Role;
import com.vaadin.hilla.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO{
        @NotEmpty(message = "The name is required")
        private String name;
        @NotEmpty(message = "The lastname is required")
        private String lastname;
        @NotEmpty(message = "The name is required")
        @Email(message = "The email address is invalid")
        private String email;
        @NotEmpty(message = "The password is required")
        private String password;
        @NotEmpty(message = "The birthday is required")
        @Past(message = "Date of birth must be before today")
        private LocalDate birthday;
        @Builder.Default
        private Role role = Role.USER;
}
