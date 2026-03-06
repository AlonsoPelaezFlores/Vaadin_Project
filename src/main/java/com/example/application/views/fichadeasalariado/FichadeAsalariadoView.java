package com.example.application.views.fichadeasalariado;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Ficha de Asalariado")
@Route("ficha-de-asalariado")
@Menu(order = 1, icon = LineAwesomeIconUrl.TABLET_ALT_SOLID)
public class FichadeAsalariadoView extends Composite<VerticalLayout> {

    public FichadeAsalariadoView() {
        H3 h3 = new H3();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H4 h4 = new H4();
        HorizontalLayout layoutRow = new HorizontalLayout();
        H6 h6 = new H6();
        Paragraph textMedium = new Paragraph();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        H6 h62 = new H6();
        Paragraph textMedium2 = new Paragraph();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        TabSheet tabSheet = new TabSheet();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h3.setText("Fitta assegurat");
        h3.setWidth("max-content");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setHeight("170px");
        h4.setText("Dades assegurat");
        h4.setWidth("max-content");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("40px");
        h6.setText("Num. assegurat");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, h6);
        h6.setWidth("140px");
        textMedium.setText("023546P");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, textMedium);
        textMedium.setWidth("120px");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("40px");
        h62.setText("Nom assegurat");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, h62);
        h62.setWidth("140px");
        textMedium2.setText("023546P");
        layoutRow2.setAlignSelf(FlexComponent.Alignment.CENTER, textMedium2);
        textMedium2.setWidth("120px");
        textMedium2.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        tabSheet.setWidth("100%");
        setTabSheetSampleData(tabSheet);
        getContent().add(h3);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h4);
        layoutColumn2.add(layoutRow);
        layoutRow.add(h6);
        layoutRow.add(textMedium);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(h62);
        layoutRow2.add(textMedium2);
        getContent().add(layoutRow3);
        layoutRow3.add(tabSheet);
    }

    private void setTabSheetSampleData(TabSheet tabSheet) {
        tabSheet.add("Dashboard", new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment", new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping", new Div(new Text("This is the Shipping tab content")));
    }
}
