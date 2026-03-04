package com.example.vaadin_project.backend.dto;

import com.example.vaadin_project.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthday;
    private Role role;
}
