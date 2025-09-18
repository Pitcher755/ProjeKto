package com.github.com.Pitcher755.projekto.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.RolesAllowed;

/**
 * Vista de redirección para la raíz ("/").
 * 
 * Responsabilidades:
 * - Redirigir a login si el usuario NO está autenticado
 * - Redirigir a la vista principal si el usuario SÍ está autenticado
 * - Evitar conflictos entre Spring Security y Vaadin
 */
@Route("/main")
@PageTitle("Inicio - Projekto")
@RolesAllowed("USER")
public class MainView extends VerticalLayout {

    public MainView() {
        // Título y contenido
        add(new H1("¡Bienvenido a Projekto!"));
        add(new Paragraph("Sistema de gestión de proyectos colaborativos"));

        // Botón de cerrar sesión
        Button logoutButton = new Button("Cerrar sesión", event -> {
            UI.getCurrent().getPage().setLocation("/logout");
        });
        logoutButton.addClassName("btn-secondary");

        add(logoutButton);

        // Configuración de layout
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

}
