package com.example.vaadin_project;

import com.example.vaadin_project.backend.dto.CreateUserDTO;
import com.example.vaadin_project.backend.entity.Role;
import com.example.vaadin_project.backend.repo.UserRepository;
import com.example.vaadin_project.backend.service.UserService;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@Theme("principal")
@SpringBootApplication
public class VaadinProjectApplication implements AppShellConfigurator{

    public static void main(String[] args) {
        SpringApplication.run(VaadinProjectApplication.class, args);
    }
    @Bean
    public CommandLineRunner initData(UserService userService, UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userService.create(new CreateUserDTO("Admin", "User", "admin@test.com", "password123", LocalDate.of(1990, 1, 1), Role.ADMIN));
                userService.create(new CreateUserDTO("John", "Doe", "john@test.com", "password123", LocalDate.of(1995, 5, 15), Role.USER));
                userService.create(new CreateUserDTO("Jane", "Smith", "jane@test.com", "password123", LocalDate.of(1998, 8, 20), Role.USER));
                userService.create(new CreateUserDTO("Carlos", "Garcia", "carlos@test.com", "password123", LocalDate.of(1992, 3, 10), Role.USER));
                userService.create(new CreateUserDTO("Maria", "Lopez", "maria@test.com", "password123", LocalDate.of(2000, 7, 25), Role.USER));
            }
        };
    }
}
