package com.example.vaadin_project.ui.forms;

import com.example.vaadin_project.backend.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;


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
        subMenu.addItem("Logout", e -> UI.getCurrent().navigate("logout"));

        getStyle().setPadding("2rem");
    }
}
