package com.example.vaadin_project.ui.layouts;

import com.example.vaadin_project.backend.dto.UserProfileDTO;
import com.example.vaadin_project.backend.service.UserService;
import com.example.vaadin_project.ui.forms.UserMenuBar;
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
import jakarta.annotation.security.PermitAll;

@Layout
@PermitAll
public class MainLayout extends AppLayout {
    private final UserService userService;
    public MainLayout(UserService userService){
        this.userService = userService;

        DrawerToggle drawer = new DrawerToggle();
        HorizontalLayout header = getHeader();

        UserProfileDTO user = userService.getCurrentUserDTO();
        boolean isAdmin = user.getRole().name().equals("ADMIN");
        SideNav nav;
        if (isAdmin){
            nav = buildAdminMenu();
        }else {
            nav = buildUserMenu();
        }

        Scroller scroller = new Scroller(nav);
        addToDrawer(scroller);
        addToNavbar(drawer, header);

        setPrimarySection(Section.DRAWER);
    }

    private SideNav buildAdminMenu() {
        SideNav nav = new SideNav();
        nav.addItem(
                new SideNavItem("Home","user",
                VaadinIcon.USER.create()));
        nav.addItem(
                new SideNavItem("Dashboard", "admin",
                        VaadinIcon.DASHBOARD.create()));

        return nav;
    }

    private SideNav buildUserMenu() {
        SideNav nav = new SideNav();
        nav.addItem(
                new SideNavItem("Home", "user",
                        VaadinIcon.USER.create()));
        return nav;
    }

    private HorizontalLayout getHeader(){
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setAlignItems(Alignment.CENTER);
        header.setPadding(true);
        header.getThemeList().clear();

        H1 title = new H1("Dashboard");
        title.getStyle().set("font-size", "1.125rem").set("margin", "0");

        UserMenuBar menuBar = new UserMenuBar(userService);


        header.add(title);
        header.setFlexGrow(1,title);
        header.add(menuBar);

        return header;
    }
}
