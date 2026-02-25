package com.example.vaadin_project.frontend;

import com.example.vaadin_project.backend.ServiceUser;
import com.example.vaadin_project.backend.User;
import com.example.vaadin_project.backend.UserDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



@Route(value = "", layout = MainLayout.class)
@PageTitle("Home User")
public class UserView extends VerticalLayout {
    private final ServiceUser userService;

    public UserView(ServiceUser userService){
        this.userService = userService;
        Grid<User> grid = new Grid<>(User.class, false);
        grid.addColumn(User::getId).
                setHeader("ID").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getName)
                .setHeader("Nombres").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getLastname)
                .setHeader("Apellido").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(User::getAge)
                .setHeader("Edad").setSortable(true).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(u -> u.getAge() >= 18)
                .setHeader("Es mayor de edad? ").setTextAlign(ColumnTextAlign.CENTER);

        grid.addComponentColumn(u -> {
            HorizontalLayout group = new HorizontalLayout();
            group.setPadding(true);
            Button btnModify = new Button(VaadinIcon.EDIT.create(), btnModifyClickEvent -> {
                getFormModify(u.getId()).getChildren();
            });
            Button btnDelete = new Button(VaadinIcon.TRASH.create(), btnDeleteClickEvent -> {
                userService.delete(u.getId());
                Notification.show("Se ha eliminado al usuario con ID " + u.getId()).setPosition(Notification.Position.TOP_END);
                grid.setItems(userService.findAll());
            });
            group.add(btnModify,btnDelete);
           return group;
        }).setHeader("Acciones").setTextAlign(ColumnTextAlign.CENTER);

        grid.setItems(userService.findAll());
        grid.getStyle().setAlignItems(Style.AlignItems.START);
        grid.setSizeFull();

        add(grid);
    }
    public FormLayout getFormModify(Long id){

        TextField name = new TextField("Name");
        name.setPlaceholder("Introduce el nuevo nombre");
        TextField lastname = new TextField("Lastname");
        lastname.setPlaceholder("Introduce el nuevo apellido");
        NumberField age = new NumberField("Introduce tu edad");
        age.setMin(0);
        age.setMax(99);
        HorizontalLayout layout = new HorizontalLayout();
        Button btnCancel = new Button("Cancelar");
        btnCancel.addThemeVariants(ButtonVariant.AURA_TERTIARY);
        Button btnSave = new Button("Guardar", btnSaveClickEvent ->{
            modifyUser(id,new UserDTO(name.toString(),lastname.toString(),age.getValue().intValue()));
        });
        btnSave.addThemeVariants(ButtonVariant.AURA_PRIMARY);
        layout.add(btnCancel,btnSave);
        layout.setAlignItems(Alignment.END);

        FormLayout formLayout = new FormLayout();
        formLayout.setAutoResponsive(true);
        formLayout.addFormRow(name);
        formLayout.addFormRow(lastname);
        formLayout.addFormRow(age);
        formLayout.addFormRow(layout);

        return formLayout;
    }

    private void modifyUser(Long id, UserDTO userDTO) {

    }
}
