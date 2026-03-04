package com.example.vaadin_project.ui.views;

import com.example.vaadin_project.backend.dto.ChangePasswordDTO;
import com.example.vaadin_project.backend.dto.UserProfileDTO;
import com.example.vaadin_project.backend.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "settings")
@PermitAll
public class SettingsView extends VerticalLayout {

    private final TextField name = new TextField("Name");
    private final TextField lastname = new TextField("Last Name");
    private final EmailField email = new EmailField("Email");
    private final DatePicker birthday = new DatePicker("Birthday");

    private final PasswordField currentPassword = new PasswordField("Current Password");
    private final PasswordField dialogCurrentPassword = new PasswordField("Current Password");
    private final PasswordField dialogNewPassword = new PasswordField("New Password");
    private final PasswordField dialogConfirmPassword = new PasswordField("Confirm Password");

    private final Binder<UserProfileDTO> binderUserModifyForm = new Binder<>(UserProfileDTO.class);

    private final UserService userService;
    private UserProfileDTO currentUser;

    public SettingsView(UserService userService) {
        this.userService = userService;
        currentUser = userService.getCurrentUserDTO();

        setSizeFull();
        setPadding(true);
        setMaxWidth("50%");
        setWidth("100%");
        getStyle()
                .set("margin-left", "auto")
                .set("margin-right", "auto");

        add(buildPersonalSection(), buildPasswordSection());
    }

    private void setUpBinder() {

        name.setValue(currentUser.getName());
        lastname.setValue(currentUser.getLastname());
        email.setValue(currentUser.getEmail());
        birthday.setValue(currentUser.getBirthday());

        name.setReadOnly(true);
        lastname.setReadOnly(true);
        email.setReadOnly(true);
        birthday.setReadOnly(true);

    }
    private Component buildPersonalSection() {
        H3 title = new H3("Personal Information");

        setUpBinder();

        FormLayout form = new FormLayout(name,lastname,email,birthday);
        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));

        Button editButton = new Button("Edit", e -> openDialogUserForm());

        return new VerticalLayout(title, form, editButton);
    }

    private void openDialogUserForm() {

        Dialog dialogForm = new Dialog();
        dialogForm.setHeaderTitle("Modify Personal Information");
        dialogForm.setWidth("400px");
        dialogForm.setMinHeight("450px");

        TextField dialogName = new TextField("Name");
        TextField dialogLastname = new TextField("Last Name");
        EmailField dialogEmail = new EmailField("Email");
        DatePicker dialogBirthday = new DatePicker("Birthday");

        dialogName.setValue(name.getValue());
        dialogLastname.setValue(lastname.getValue());
        dialogEmail.setValue(email.getValue());
        dialogBirthday.setValue(birthday.getValue());

        dialogName.setReadOnly(true);
        dialogLastname.setReadOnly(true);

        binderUserModifyForm.forField(dialogEmail)
                .asRequired("El email es obligatorio")
                .bind(UserProfileDTO::getEmail,UserProfileDTO::setEmail);
        binderUserModifyForm.forField(dialogBirthday)
                .asRequired("La fecha de nacimiento es obligatorio")
                .bind(UserProfileDTO::getBirthday, UserProfileDTO::setBirthday);

        dialogForm.add(new FormLayout(dialogName,dialogLastname,dialogEmail,dialogBirthday));

        Button cancelButton = new Button("Cancel", e -> dialogForm.close());

        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        Button saveButton = new Button("Save",
                e -> {
                    onSavePersonalInformation(dialogForm);
                    UI.getCurrent().getPage().reload();
                });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        dialogForm.getFooter().add(cancelButton);
        dialogForm.getFooter().add(saveButton);

        dialogForm.open();
    }

    private void onSavePersonalInformation(Dialog dialog){

        try{
            UserProfileDTO userSaved = new UserProfileDTO();
            userSaved.setId(currentUser.getId());
            binderUserModifyForm.writeBean(userSaved);
            userService.modifyPersonalInformation(userSaved);
            Notification.show("User modified successfully");
            dialog.close();
        }catch (Exception e){
            Notification.show("Validation error: " + e.getMessage());
        }
    }

    private Component buildPasswordSection() {
        currentPassword.getElement().setAttribute("autocomplete", "new-password");

        H3 title = new H3("Password");

        currentPassword.setReadOnly(true);
        currentPassword.setRevealButtonVisible(false);
        currentPassword.setValue("••••••••");

        FormLayout form = new FormLayout(currentPassword);
        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        Button editButton=new Button("Edit", e -> openDialogPasswordForm());

        return new VerticalLayout(title, form, editButton);
    }

    private void openDialogPasswordForm() {
        Dialog dialogPassword = new Dialog();

        dialogPassword.setHeaderTitle("Change Password");
        dialogPassword.setWidth("400px");
        dialogPassword.setMinHeight("350px");

        FormLayout formLayout = new FormLayout(dialogCurrentPassword,dialogNewPassword, dialogConfirmPassword);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));
        dialogPassword.add(formLayout);

        Button cancelButton = new Button("Cancel", e-> dialogPassword.close());
        Button saveButton = new Button("Save", e -> {
            onSaveNewPassword(dialogPassword);
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialogPassword.getFooter().add(cancelButton,saveButton);
        dialogPassword.open();

    }

    private void onSaveNewPassword(Dialog dialog){
        UserProfileDTO userProfileDTO  = userService.getCurrentUserDTO();
        boolean successful = userService.changePassword(userProfileDTO.getId(),
                new ChangePasswordDTO(
                        dialogCurrentPassword.getValue(),
                        dialogNewPassword.getValue(),
                        dialogConfirmPassword.getValue()));
        if (successful) dialog.close();
    }
}
