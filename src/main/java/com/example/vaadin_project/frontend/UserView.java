package com.example.vaadin_project.frontend;

import com.example.vaadin_project.backend.UserService;
import com.example.vaadin_project.backend.User;
import com.example.vaadin_project.backend.CreateUserDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


@Route(value = "users", layout = MainLayout.class)
@PageTitle("Home User")
@PermitAll
public class UserView extends VerticalLayout {

    private final UserService userService;
    private final Grid<User> grid = new Grid<>(User.class,false);

    public UserView(UserService userService){
        this.userService = userService;
        Button addUserBtn = new Button("Add", buttonClickEvent -> {
            openUserDialog(null);
        });
        addUserBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addUserBtn.getStyle().setMarginRight("1rem");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.add(addUserBtn);
        layout.setJustifyContentMode(JustifyContentMode.END);

        Grid<User> grid = createGridUser();
        refreshItems();
        grid.setSizeFull();
        add(layout,grid);
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
                openUserDialog(null);
            });
            btnModify.getStyle().setColor("#DEDB7C");
            Button btnDelete = new Button(VaadinIcon.TRASH.create(), btnDeleteClickEvent -> {
                //deleteUser(user.getId());
            });
            btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            HorizontalLayout group = new HorizontalLayout();
            group.setPadding(true);
            group.add(btnModify,btnDelete);
            group.getStyle().setMarginLeft("2rem");
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
        dialog.setHeaderTitle(user!=null? "Modifying User":"Adding New User");
        dialog.setWidth("400px");
        dialog.setHeight("350px");

        UserForm userForm = new UserForm();
        //userForm.setUser(user);

        dialog.add(userForm);

        Button btnCancel = new Button("Cancel", e -> dialog.close());
        Button btnSave = new Button("Save", e -> dialog.close());

        dialog.getFooter().add(btnCancel);
        dialog.getFooter().add(btnSave);

        dialog.open();
    }

/*     private Button createSaveButton(UserForm userForm, Dialog dialog) {
        return new Button("Save", e -> {
            if (userForm.save()){
                User user = userForm.getUser();

                CreateUserDTO dto = CreateUserDTO.builder()
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .birthday(user.getBirthday())
                        .build();

                if (user.getId()==null){
                    userService.create(dto);
                    Notification.show("A new user has been created",3000, Notification.Position.TOP_END);
                    refreshItems();
                }else {
                    userService.modify(user.getId(),dto);

                    Notification.show("A user with ID %d has been modified ".formatted(user.getId()),
                                    3000, Notification.Position.TOP_END);
                    refreshItems();
                }
                dialog.close();
            }
        });
    }
    */
}
