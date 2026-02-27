package com.example.vaadin_project.frontend;

import com.example.vaadin_project.backend.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final TextField name = new TextField("Name");
    private final TextField lastname = new TextField("Last Name");
    private final DatePicker birthday = new DatePicker("Birthday");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");

    public RegisterView(UserService userService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H2 title = new H2("Create Account");

        Button saveButton = new Button("Register", e -> {
            if (!password.getValue().equals(confirmPassword.getValue())) {
                Notification.show("Passwords do not match");
                return;
            }
            // userService.createUser(...)
            Notification.show("User registered successfully");
            getUI().ifPresent(ui -> ui.navigate("login"));
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Anchor loginLink = new Anchor("/login", "Already have an account? Go to Sign In");

        FormLayout form = new FormLayout(name, lastname, birthday, email, password, confirmPassword);
        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        form.setMaxWidth("600px");

        add(title, form, saveButton, loginLink);
    }
}
