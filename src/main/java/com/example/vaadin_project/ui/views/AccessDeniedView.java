package com.example.vaadin_project.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "/access-denied", autoLayout = false)
@AnonymousAllowed
public class AccessDeniedView extends VerticalLayout {
    public AccessDeniedView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(
                new H2("Access Denied"),
                new Paragraph("You don't have permission to access this page."),
                new Button("Go to Login", e -> getUI().ifPresent(ui -> ui.navigate("login")))
        );
    }
}
