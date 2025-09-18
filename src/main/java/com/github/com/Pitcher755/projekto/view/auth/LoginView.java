package com.github.com.Pitcher755.projekto.view.auth;

import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 * Vista de login basada en el componente {@link LoginForm} de Vaadin.
 * 
 * Importante:
 * - El formulario hace POST a "/login" (configurado por Spring Security).
 * - Spring Security redirige a la URL original tras autenticación.
 */
@Route("login")
@PageTitle("Inicio de sesión - Projekto")
@CssImport("./styles/shared-styles.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken)) {
            event.rerouteTo("main");
        }
    }

    public LoginView() {
        // ELIMINAR la redirección del constructor
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(createTitle(), createLoginForm(), createRegisterLink());
        handleErrorParameter();
    }

    private Component createTitle() {
        H1 title = new H1("Projekto");
        title.getStyle()
                .set("color", "var(--projekto-primary)")
                .set("margin-bottom", "var(--projekto-spacing-lg)")
                .set("font-size", "var(--projekto-font-size-2xl)");
        return title;
    }

    private Component createLoginForm() {
        LoginForm login = new LoginForm();

        // Indicar la acción a la que se hace POST (Spring Security formLogin espera
        // /login por defecto)
        login.setAction("perform_login");

        // LoginForm automáticamente muestra error si existe el parámetro ?error en la
        // URL.
        // Spring Security añadirá ese parámetro en caso de fallo.
        login.setForgotPasswordButtonVisible(false);

        // Estilizar el formulario
        login.getStyle()
                .set("width", "100%")
                .set("max-width", "400px");

        return login;
    }

    private Component createRegisterLink() {
        HorizontalLayout registerLayout = new HorizontalLayout();
        registerLayout.setAlignItems(Alignment.CENTER);
        registerLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        Paragraph text = new Paragraph("¿No tienes cuenta?");
        text.getStyle()
                .set("margin", "0")
                .set("color", "var(--projekto-gray-600)");

        RouterLink registerLink = new RouterLink("Regístrate aquí", RegisterView.class);
        registerLink.getStyle()
                .set("margin-left", "var(--projekto-spacing-xs)")
                .set("color", "var(--projekto-primary)")
                .set("text-decoration", "none")
                .set("font-weight", "500");

        registerLink.addClassName("btn-secondary");

        registerLayout.add(text, registerLink);
        return registerLayout;
    }

    private void handleErrorParameter() {
        List<String> errorParams = UI.getCurrent().getInternals()
                .getActiveViewLocation().getQueryParameters().getParameters().get("error");

        if (errorParams != null && !errorParams.isEmpty()) {
            Notification.show("Credenciales incorrectas", 3000, Notification.Position.MIDDLE);
        }
    }
}
