package com.github.com.Pitcher755.projekto.view;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

/**
 * Vista de redirección para la raíz ("/").
 * 
 * Responsabilidades:
 * - Redirigir a login si el usuario NO está autenticado
 * - Redirigir a la vista principal si el usuario SÍ está autenticado
 * - Evitar conflictos entre Spring Security y Vaadin
 */
@Route("") // Ruta raíz
public class RootRedirectView extends Div implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);

        if (isAuthenticated) {
            // Usuario autenticado → redirigir a vista principal
            event.rerouteTo("main");
        } else {
            // Usuario no autenticado → redirigir a login
            event.rerouteTo("login");
        }
    }
}