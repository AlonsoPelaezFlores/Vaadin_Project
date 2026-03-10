package com.example.application.views.employee;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class ContactInformationTab extends Composite<VerticalLayout> {
    public ContactInformationTab(){
        getContent().add(Contacts(), Directions());
    }
    public VerticalLayout Contacts(){

        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        H3 title = new H3("Contactos");
        Button btnAddContact = new Button("Añadir Contacto");
        btnAddContact.addClassName("btn-add");

        header.add(title,btnAddContact);
        header.setFlexGrow(1,title);

        HorizontalLayout container = new HorizontalLayout();
        HorizontalLayout layout1 = createCardContact();
        HorizontalLayout layout2 = createCardContact();
        container.setFlexGrow(1,layout1);
        container.setFlexGrow(1,layout2);
        container.add(layout1,layout2);
        container.setWidthFull();
        return new VerticalLayout(header,container);
    }
    public HorizontalLayout createCardContact (){
        HorizontalLayout cardContact = new HorizontalLayout();
        Button btnModify = new Button(VaadinIcon.PENCIL.create());
        Button btnDelete = new Button(VaadinIcon.TRASH.create());
        btnDelete.addClassName("btn-delete");
        btnModify.addClassName("btn-edit");
        HorizontalLayout groupBtn = new HorizontalLayout(btnModify, btnDelete);
        groupBtn.addClassName("btn-group");
        cardContact.add(createFormContact(),groupBtn);
        cardContact.addClassName("card-container");
        cardContact.setWidthFull();
        return cardContact;
    }
    public FormLayout createFormContact (){
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(new Span("xmorera@cass.ad"),new Span("Correo electrónico"));
        formLayout.addFormItem(new Span("833699"),new Span("Teléfono Fijo"));
        formLayout.addFormItem(new Span("622552"),new Span("Teléfono móvil"));
        formLayout.addFormItem(new Span("- - - -"),new Span("Fax"));
        formLayout.addFormItem(new Span("Assegurat"),new Span("Tipo"));
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1),
                new FormLayout.ResponsiveStep("500px",2));
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
        HorizontalLayout layout1 = createCardDirection();
        HorizontalLayout layout2 = createCardDirection();
        container.setFlexGrow(1,layout1);
        container.setFlexGrow(1,layout2);
        container.add(layout1,layout2);
        return new VerticalLayout(header,container);
    }
    public HorizontalLayout createCardDirection(){

        HorizontalLayout cardDirection = new HorizontalLayout();
        Button btnModify = new Button(VaadinIcon.PENCIL.create());
        Button btnDelete = new Button(VaadinIcon.TRASH.create());
        btnDelete.addClassName("btn-delete");
        btnModify.addClassName("btn-edit");
        HorizontalLayout groupBtn = new HorizontalLayout(btnModify, btnDelete);
        groupBtn.addClassName("btn-group");
        cardDirection.add(createFormDirection(),groupBtn);
        cardDirection.addClassName("card-container");
        cardDirection.setWidthFull();
        return cardDirection;
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
