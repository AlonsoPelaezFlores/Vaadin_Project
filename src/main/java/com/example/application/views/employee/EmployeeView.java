package com.example.application.views.employee;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Ficha de Asalariado")
@Route("ficha-de-asalariado")
@Menu(order = 1, icon = LineAwesomeIconUrl.TABLET_ALT_SOLID)
public class EmployeeView extends Composite<VerticalLayout> {

    public EmployeeView() {
        getContent().getStyle().setBackgroundColor("#F5F5F5");

        H3 title = new H3("Fitta Assegurat");
        title.getStyle()
                .setPaddingBottom("1rem")
                .setPaddingTop("1rem");
        getContent().add(title);
        getContent().add(ensuredData(),createTabSheet());
    }
    private VerticalLayout ensuredData(){
        VerticalLayout layout = new VerticalLayout();
        H4 title = new H4("Dades Assegurat");
        layout.add(title);
        FormLayout dataUser = new FormLayout();
        dataUser.addFormItem(new Span("002434P"), new Span("Nùm. assegurat"));
        dataUser.addFormItem(new Span("MORERA MARTINEZ, XAVIER"),new Span("Nom assegurat"));
        dataUser.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));

        layout.add(dataUser);
        layout.addClassName("card-container");
        return layout;
    }

    private TabSheet createTabSheet() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.add("Dades generals", new GeneralDataTab());
        tabSheet.add("Dades contacte", new ContactInformationTab());
        tabSheet.add("Assegurats indirectes", new Div(new Text("This is the Shipping tab content")));
        tabSheet.add("Dades bancàries", new Div(new Text("This is the Shipping tab content")));
        tabSheet.add("Historial laboral", new Div(new Text("This is the Shipping tab content")));
        tabSheet.setClassName("tab-cass");
        return tabSheet;
    }
}
