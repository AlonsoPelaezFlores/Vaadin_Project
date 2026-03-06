package com.example.vaadin_project.backend.config;

import com.example.vaadin_project.ui.views.AccessDeniedView;
import com.example.vaadin_project.ui.views.LoginView;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SecurityServerInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            uiEvent.getUI().addBeforeEnterListener(enterEvent -> {
                if (enterEvent.getNavigationTarget() != AccessDeniedView.class
                        && !enterEvent.getNavigationTarget().isAnnotationPresent(AnonymousAllowed.class)) {

                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    if (auth == null || !auth.isAuthenticated()) {
                        enterEvent.rerouteTo(LoginView.class);
                        return;
                    }

                    RolesAllowed rolesAllowed = enterEvent.getNavigationTarget()
                            .getAnnotation(RolesAllowed.class);
                    if (rolesAllowed != null) {
                        boolean hasRole = auth.getAuthorities().stream()
                                .anyMatch(a -> Arrays.stream(rolesAllowed.value())
                                        .anyMatch(role -> a.getAuthority().equals("ROLE_" + role)));
                        if (!hasRole) {
                            enterEvent.rerouteTo(AccessDeniedView.class);
                        }
                    }
                }
            });
        });
    }
}
