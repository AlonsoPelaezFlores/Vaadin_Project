package com.example.vaadin_project.ui.views;

import com.example.vaadin_project.backend.dto.UserProfileDTO;
import com.example.vaadin_project.backend.service.UserService;
import com.example.vaadin_project.ui.layouts.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "user")
@PermitAll
public class UserView extends VerticalLayout {
    public UserView(UserService userService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);;

        UserProfileDTO user = userService.getCurrentUserDTO();
        add(
                new H2("Welcome, " + user.getName() + "!"),
                new Paragraph("You are logged in as " + user.getRole())
        );
    }
}
