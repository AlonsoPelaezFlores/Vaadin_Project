package com.example.vaadin_project.ui.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "login", autoLayout = false)
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver
{
    private final LoginForm loginForm= new LoginForm();

    public LoginView(){
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        loginForm.setForgotPasswordButtonVisible(false);
        H1 title = new H1("Log In");
        title.getStyle().setAlignItems(Style.AlignItems.CENTER);
        Anchor registerLink = new Anchor("/register","Don't have an account ? Sign Up ");
        loginForm.setAction("login");
        add( title,loginForm, registerLink);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation().getQueryParameters()
                .getParameters().containsKey("error")) {
            loginForm.setError(true);
        }
    }
}
