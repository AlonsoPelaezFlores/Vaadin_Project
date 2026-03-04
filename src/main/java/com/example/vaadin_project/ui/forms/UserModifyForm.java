package com.example.vaadin_project.ui.forms;

import com.example.vaadin_project.backend.entity.Role;
import com.example.vaadin_project.backend.entity.User;
import com.example.vaadin_project.backend.dto.UserProfileDTO;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import lombok.Getter;

public class UserModifyForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField lastname = new TextField("Lastname");
    EmailField email = new EmailField("Email");
    DatePicker birthDay = new DatePicker("Birthday");
    ComboBox<Role> role = new ComboBox<>("Role");

    BeanValidationBinder<UserProfileDTO> binder = new BeanValidationBinder<>(UserProfileDTO.class);
    @Getter
    private UserProfileDTO user;
    /**
     * bindInstanceField - Valida los campos del formulario y
     * enlaza con los atributos User
     */

    public UserModifyForm(){
        binder.bindInstanceFields(this);
        role.setItems(Role.values());
        add(name,lastname,email,birthDay,role);
    }
    /**
     * Si hay un User envia los datos al formulario con readBean()
     * backend -> frontend
     * De lo contrario se crea un nuevo User.
     */

    public void setUser(User user){
        this.user = new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getBirthday(),
                user.getRole()
        );
        binder.readBean(this.user);
    }
    /**
     * Si pasa las validaciones que contiene User rellena los datos
     * de los campos en el User.
     * De lo contrario lanza una exception no se guardara nada.
     * */

    public boolean save(){
        try{
            binder.writeBean(user);
            return true;
        }catch (ValidationException e){
            return false;
        }
    }
}
