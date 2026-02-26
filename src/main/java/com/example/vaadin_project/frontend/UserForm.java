package com.example.vaadin_project.frontend;

import com.example.vaadin_project.backend.User;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

public class UserForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField lastname = new TextField("Lastname");
    IntegerField age = new IntegerField("Age");
    private User user;

    BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);

    /**
     * bindInstanceField - Valida los campos del formulario y
     * enlaza con los atributos User
     */
    public UserForm(){
        binder.bindInstanceFields(this);
        add(name,lastname,age);
    }
    /**
     * Si hay un User envia los datos al formulario con readBean()
     * backend -> frontend
     * De lo contrario se crea un nuevo User.
     */
    public void setUser(User user){
        this.user = (user!=null)?user : new User();
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
    public User getUser(){
        return user;
    }

}
