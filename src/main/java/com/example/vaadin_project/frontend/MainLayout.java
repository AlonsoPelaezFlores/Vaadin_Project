package com.example.vaadin_project.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;

@Layout
public class MainLayout extends AppLayout {
    public MainLayout(){
        DrawerToggle drawer = new DrawerToggle();
        H1 title = new H1("Dashboard");
        title.getStyle().set("font-size", "1.125rem").set("margin", "0");

        HorizontalLayout header = new HorizontalLayout(title);
        header.setWidthFull();
        header.setPadding(true);
        header.getThemeList().clear();

        SideNav nav = getTabs();
        Scroller scroller = new Scroller(nav);

        addToDrawer(scroller);
        addToNavbar(drawer, header);

        setPrimarySection(Section.DRAWER);
    }
    private SideNav getTabs() {
        SideNav nav = new SideNav();
        nav.addItem(
                new SideNavItem("Users", "/",
                        VaadinIcon.USER.create()));
        return nav;
    }
}
