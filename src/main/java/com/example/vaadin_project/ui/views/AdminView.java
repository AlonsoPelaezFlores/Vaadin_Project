package com.example.vaadin_project.ui.views;

import com.example.vaadin_project.backend.service.UserService;
import com.example.vaadin_project.backend.entity.User;
import com.example.vaadin_project.ui.layouts.MainLayout;
import com.example.vaadin_project.ui.forms.UserModifyForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;


@Route(value = "admin", layout = MainLayout.class)
@PageTitle("Admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout{

    private final UserService userService;
    private final Grid<User> grid = new Grid<>(User.class,false);

    public AdminView(UserService userService){
        this.userService = userService;

        Grid<User> grid = createGridUser();
        expand(grid);
        refreshItems();
        add(grid);
    }
    private void refreshItems(){
        grid.setItems(userService.findAll());
    }

    private Grid<User> createGridUser() {

        grid.addColumn(User::getId).
                setHeader("ID").setTextAlign(ColumnTextAlign.CENTER).setSortable(true);
        grid.addColumn(User::getName)
                .setHeader("Name").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getLastname)
                .setHeader("Lastname").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getEmail)
                .setHeader("Email").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getRole)
                .setHeader("Role").setTextAlign(ColumnTextAlign.CENTER);

        grid.addComponentColumn(user -> {

            Button btnModify = new Button(VaadinIcon.EDIT.create(), btnModifyClickEvent -> {
                openUserDialog(user);
            });
            btnModify.getStyle().setColor("#DEDB7C");
            Button btnDelete = new Button(VaadinIcon.TRASH.create(), e  -> deleteUser(user.getId()));
            btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR)
            ;
            HorizontalLayout group = new HorizontalLayout();
            group.setPadding(true);
            group.add(btnModify,btnDelete);
            return group;
        }).setHeader("Actions").setTextAlign(ColumnTextAlign.CENTER);
        return grid;
    }

    private void deleteUser(Long userId) {
        userService.delete(userId);
        Notification.show("A user with ID %d has been deleted ".formatted(userId),3000, Notification.Position.TOP_END);
        refreshItems();
    }

    private void openUserDialog(User user){

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Modify User");
        dialog.setWidth("450px");
        dialog.setHeight("auto");

        UserModifyForm userModifyForm = new UserModifyForm();
        userModifyForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));
        userModifyForm.setUser(user);

        dialog.add(userModifyForm);

        Button btnCancel = new Button("Cancel", e -> dialog.close());

        Button btnSave = createSaveButton(userModifyForm,dialog);
        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        dialog.getFooter().add(btnCancel);
        dialog.getFooter().add(btnSave);

        dialog.open();
    }
    private Button createSaveButton(UserModifyForm userModifyForm, Dialog dialog) {
        return new Button("Save", e -> {
            if (userModifyForm.save()){

                userService.modifyUserInformation(userModifyForm.getUser());
                Notification.show("A user with ID %d has been modified ".formatted(userModifyForm.getUser().getId()),
                                    3000, Notification.Position.TOP_END);
                refreshItems();
                }
                dialog.close();
        });
    }
}
