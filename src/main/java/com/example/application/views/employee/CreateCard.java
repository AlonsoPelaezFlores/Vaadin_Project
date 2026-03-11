package com.example.application.views.employee;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CreateCard extends Div {
    public CreateCard(FormLayout form){

        Button btnModify = new Button(VaadinIcon.PENCIL.create());
        Button btnDelete = new Button(VaadinIcon.TRASH.create());

        btnDelete.addClassName("btn-delete");
        btnModify.addClassName("btn-edit");
        HorizontalLayout groupBtn = new HorizontalLayout(btnModify, btnDelete);
        groupBtn.addClassName("btn-group");
        HorizontalLayout container = new HorizontalLayout(form,groupBtn);
        add(container);
        addClassName("card-container");
    }
}
