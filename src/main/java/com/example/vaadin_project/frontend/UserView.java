package com.example.vaadin_project.frontend;

import com.example.vaadin_project.backend.ServiceUser;
import com.example.vaadin_project.backend.User;
import com.example.vaadin_project.backend.UserDTO;
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



@Route(value = "", layout = MainLayout.class)
@PageTitle("Home User")
public class UserView extends VerticalLayout {

    private final ServiceUser serviceUser;
    private final Grid<User> grid = new Grid<>(User.class,false);

    public UserView(ServiceUser userService){
        this.serviceUser = userService;
        Button addUserBtn = new Button("Agregar", buttonClickEvent -> {
            openUserDialog(null);
        });
        addUserBtn.addThemeVariants(ButtonVariant.AURA_PRIMARY);
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
        grid.setItems(serviceUser.findAll());
    }

    private Grid<User> createGridUser() {

        grid.addColumn(User::getId).
                setHeader("ID").setTextAlign(ColumnTextAlign.CENTER).setSortable(true);
        grid.addColumn(User::getName)
                .setHeader("Nombre").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getLastname)
                .setHeader("Apellido").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getAge)
                .setHeader("Edad").setSortable(true).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(u -> u.getAge() >= 18)
                .setHeader("Es mayor de edad? ").setTextAlign(ColumnTextAlign.CENTER);

        grid.addComponentColumn(user -> {

            Button btnModify = new Button(VaadinIcon.EDIT.create(), btnModifyClickEvent -> {
                openUserDialog(user);
            });
            btnModify.getStyle().setColor("#DEDB7C");
            Button btnDelete = new Button(VaadinIcon.TRASH.create(), btnDeleteClickEvent -> {
                deleteUser(user.getId());
            });
            btnDelete.addThemeVariants(ButtonVariant.AURA_DANGER);
            HorizontalLayout group = new HorizontalLayout();
            group.setPadding(true);
            group.add(btnModify,btnDelete);
            group.getStyle().setMarginLeft("2rem");
            return group;
        }).setHeader("Acciones").setTextAlign(ColumnTextAlign.CENTER);
        return grid;
    }

    private void deleteUser(Long userId) {
        serviceUser.delete(userId);
        Notification.show("Se ha eliminado al usuario con ID " + userId,3000, Notification.Position.TOP_END);
        refreshItems();
    }

    private void openUserDialog(User user){

        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(user!=null? "Modificando Usuario":"Agregar un nuevo usuario");
        dialog.setWidth("400px");
        dialog.setHeight("350px");

        UserForm userForm = new UserForm();
        userForm.setUser(user);

        dialog.add(userForm);

        Button btnCancel = new Button("Cancelar", e -> dialog.close());

        dialog.getFooter().add(btnCancel);
        dialog.getFooter().add(createSaveButton(userForm,dialog));

        dialog.open();
    }

    private Button createSaveButton(UserForm userForm, Dialog dialog) {
        return new Button("Aceptar", e -> {
            if (userForm.save()){
                User user = userForm.getUser();
                UserDTO dto = new UserDTO(user.getName(),user.getLastname(),user.getAge());
                if (user.getId()==null){
                    serviceUser.create(dto);
                    Notification.show("Se ha creado un nuevo usuario",3000, Notification.Position.TOP_END);
                    refreshItems();
                }else {
                    serviceUser.modify(user.getId(),dto);

                    Notification.show("Se ha modificado el usuario con Id "+user.getId(),
                                    3000, Notification.Position.TOP_END);
                    refreshItems();
                }
                dialog.close();
            }
        });
    }
}
