package com.example.vaadin_project.frontend;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@AnonymousAllowed
public class LoginView extends Composite<LoginOverlay>
{

    public LoginView(){
        getContent().setOpened(true);
        getContent().setAction("login");
        getContent().setForgotPasswordButtonVisible(false);
        getContent().setTitle("Sign In");
        getContent().setDescription("aksndkasndmal");

    }
}
