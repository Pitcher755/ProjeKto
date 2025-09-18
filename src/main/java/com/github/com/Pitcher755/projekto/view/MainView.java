package com.github.com.Pitcher755.projekto.view;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.com.Pitcher755.projekto.view.auth.LoginView;
import com.github.com.Pitcher755.projekto.view.auth.RegisterView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import jakarta.annotation.security.RolesAllowed;

@Route("")
@PageTitle("Inicio - Projekto")
@RolesAllowed("USER")
public class MainView extends VerticalLayout {

    public MainView() {
        // Título
        add(new H1("¡Bienvenido a Projekto!"));
        add(new Paragraph("Sistema de gestión de proyectos colaborativos"));

        // Verificar si el usuario está autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            // Redirigir al login si no está autenticado
            UI.getCurrent().navigate("/login");
        }

        // Navegación con RouterLink
        HorizontalLayout navigation = new HorizontalLayout();
        navigation.add(new RouterLink("Iniciar Sesión", LoginView.class));
        navigation.add(new RouterLink("Registrarse", RegisterView.class));

        add(navigation);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}