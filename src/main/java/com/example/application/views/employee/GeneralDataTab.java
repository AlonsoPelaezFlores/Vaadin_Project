package com.example.application.views.employee;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GeneralDataTab extends Composite<VerticalLayout> {
    public GeneralDataTab(){
        getContent().add(GeneralData(), IndirectInsuranceRelationship());
    }
    public VerticalLayout GeneralData(){
        H4 title = new H4("Dades Personales");
        FormLayout form = new FormLayout();
        form.addFormItem(new Span("30/07/1990"), new Span("Data dàlta"));
        form.addFormItem(new Span("ANDORRA"), new Span("Nacionalitat"));
        form.addFormItem(new Span("Masculí"), new Span("Sexe"));
        form.addFormItem(new Span("0009164"), new Span("Passaport/DNI"));
        form.addFormItem(new Span("01/09/1974"), new Span("Data de naixement"));
        form.addFormItem(new Span("CASAT"), new Span("Estas civil"));

        form.setResponsiveSteps(new FormLayout.ResponsiveStep("0",2));
        VerticalLayout layout = new VerticalLayout(title,form);
        layout.setClassName("card-container");
        return layout;
    }
    public VerticalLayout IndirectInsuranceRelationship(){

        H4 title = new H4("Relacione com assegurat indirecte");
        Grid<String[]> table = new Grid<>();
        table.addColumn(row -> row[0])
                .setHeader("Nom assegurat directe").setTextAlign(ColumnTextAlign.CENTER);
        table.addColumn(row -> row[1])
                .setHeader("Tipus assegurat indirecte").setTextAlign(ColumnTextAlign.CENTER);
        table.addColumn(row -> row[2])
                .setHeader("Data alta").setTextAlign(ColumnTextAlign.CENTER);
        table.addColumn(row -> row[3])
                .setHeader("Data baixa").setTextAlign(ColumnTextAlign.CENTER);

        VerticalLayout layout = new VerticalLayout(title,table);
        layout.setClassName("card-container");
        return layout;
    }

}
