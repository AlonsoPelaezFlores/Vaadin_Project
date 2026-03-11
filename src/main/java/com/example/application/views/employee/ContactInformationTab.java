package com.example.application.views.employee;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class ContactInformationTab extends Composite<VerticalLayout> {
    public ContactInformationTab(){
        getContent().add(Contacts(), Directions());
    }
    public Component Contacts(){

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        H3 title = new H3("Contactos");
        Button btnAddContact = new Button("Añadir Contacto");
        btnAddContact.addClassName("btn-add");

        header.add(title,btnAddContact);
        header.setFlexGrow(1,title);

        HorizontalLayout container = new HorizontalLayout();
        CreateCard card1 = new CreateCard(createFormContact());
        CreateCard card2 = new CreateCard(createFormContact());
        container.setFlexGrow(1,card1);
        container.setFlexGrow(1,card2);
        container.add(card1,card2);
        container.addClassName("container");
        return new VerticalLayout(header,container);
    }
    public FormLayout createFormContact (){

        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(new Span("xmorera@cass.ad"),new Span("Correo electrónico"));
        formLayout.addFormItem(new Span("833699"),new Span("Teléfono Fijo"));
        formLayout.addFormItem(new Span("622552"),new Span("Teléfono móvil"));
        formLayout.addFormItem(new Span("- - - -"),new Span("Fax"));
        formLayout.addFormItem(new Span("Assegurat"),new Span("Tipo"));
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));
        formLayout.addClassName("form-base-vertical");
        formLayout.setWidthFull();
        return formLayout;
    }

    public VerticalLayout Directions(){
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        H3 title = new H3("Directions");
        Button btnAddContact = new Button("Añadir Direccion");
        btnAddContact.addClassName("btn-add");

        header.add(title, btnAddContact);
        header.setFlexGrow(1,title);

        HorizontalLayout container = new HorizontalLayout();

        CreateCard card1 = new CreateCard(createFormDirection());
        CreateCard card2 = new CreateCard(createFormDirection());

        container.setFlexGrow(1,card1);
        container.setFlexGrow(1,card2);
        container.add(card1,card2);
        container.addClassName("container");
        return new VerticalLayout(header,container);
    }
    public FormLayout createFormDirection(){
        FormLayout form = new FormLayout();
        form.addFormItem(new Span("AV Esteve Albert"),new Span("Dirección"));
        form.addFormItem(new Span("Assegurat"),new Span("Tipo"));
        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));
        form.addClassName("form-base-vertical");
        form.setWidthFull();
        return form;
    }

}
