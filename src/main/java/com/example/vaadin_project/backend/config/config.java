package com.example.vaadin_project.backend.config;

import com.example.vaadin_project.ui.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class config extends VaadinWebSecurity {
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);

        http.formLogin(form -> form
                .successHandler(successHandler())
        );
        http.exceptionHandling(ex ->
                ex.accessDeniedPage("/access-denied"));

    }
    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return (request, response, authentication) -> {{
           response.sendRedirect("user");
        }};
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
