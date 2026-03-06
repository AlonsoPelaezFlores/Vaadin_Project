package com.example.vaadin_project.ui.forms;

import com.example.vaadin_project.backend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class UserMenuBar extends MenuBar {
    public UserMenuBar(UserService userService){

        String username = userService.getCurrentUserDTO().getEmail();
        Avatar avatarName = new Avatar(username);

        MenuItem userItem = addItem(avatarName);
        SubMenu subMenu = userItem.getSubMenu();

        MenuItem menuItem = subMenu.addItem(username);
        menuItem.setEnabled(false);
        subMenu.addSeparator();
        subMenu.addItem("Settings", e -> UI.getCurrent().navigate("settings"));
        subMenu.addItem("Logout", e -> {
            UI.getCurrent().getPage().setLocation("/login");
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(),null,null);
        });
    }
}
