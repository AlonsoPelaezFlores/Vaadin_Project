package com.example.vaadin_project.ui.views;

import com.example.vaadin_project.backend.dto.CreateUserDTO;
import com.example.vaadin_project.backend.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "register", autoLayout = false)
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final TextField name = new TextField("Name");
    private final TextField lastname = new TextField("Last Name");
    private final DatePicker birthday = new DatePicker("Birthday");
    private final EmailField email = new EmailField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final PasswordField confirmPassword = new PasswordField("Confirm Password");

    private final Binder<CreateUserDTO> binderRegister = new Binder<>(CreateUserDTO.class);
    private final UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        H2 title = new H2("Create Account");

        setupBinder();

        Button registerButton = new Button("Register", e -> onRegister());
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Anchor loginLink = new Anchor("/login", "Login here");
        Text text = new Text("Already have an account?");
        FormLayout form = new FormLayout(name, lastname, birthday, email, password, confirmPassword);
        form.setMaxWidth("500px");
        form.setWidth("100%");
        form.getStyle().set("margin", "0 auto");
        add(title, form, registerButton, new HorizontalLayout(text,loginLink));
    }

    private void setupBinder() {

        binderRegister.forField(name)
                .asRequired("Name is required")
                .bind(CreateUserDTO::getName,CreateUserDTO::setName);

        binderRegister.forField(lastname)
                .asRequired("Last name is required")
                .bind(CreateUserDTO::getLastname, CreateUserDTO::setLastname);

        binderRegister.forField(birthday)
                .asRequired("Birthday is required")
                .bind(CreateUserDTO::getBirthday, CreateUserDTO::setBirthday);

        binderRegister.forField(email)
                .asRequired("Email is required")
                .bind(CreateUserDTO::getEmail, CreateUserDTO::setEmail);

        binderRegister.forField(password)
                .asRequired("Password is required")
                .bind(bean -> "", CreateUserDTO::setPassword);
    }

    private void onRegister() {

        if (!password.getValue().equals(confirmPassword.getValue())) {
            Notification.show("Passwords do not match")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        CreateUserDTO dto = new CreateUserDTO();
        if (binderRegister.writeBeanIfValid(dto)) {
            userService.create(dto);
            Notification.show("User registered successfully")
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            getUI().ifPresent(ui -> ui.navigate("login"));
        }
    }
}
