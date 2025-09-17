package com.github.com.Pitcher755.projekto.view.auth;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Vista de login basada en el componente {@link LoginForm} de Vaadin.
 * 
 * Importante:
 * - El formulario hace POST a "/login" (configurado por Spring Security).
 * - Spring Security redirige a la URL original tras autenticación.
 */
@Route("login")
@PageTitle("Login - Projekto")
@CssImport("./styles/shared-styles.css")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(createTitle(), createLoginForm());
    }

    private Component createTitle() {
        H1 title = new H1("Projekto");
        title.getStyle().set("magin-bottom", "1rem");
        return title;
    }

    private Component createLoginForm() {
        LoginForm login = new LoginForm();

        // Indicar la acción a la que se hace POST (Spring Security formLogin espera
        // /login por defecto)
        login.setAction("login");

        // LoginForm automáticamente muestra error si existe el parámetro ?error en la
        // URL.
        // Spring Security añadirá ese parámetro en caso de fallo.
        login.setForgotPasswordButtonVisible(false);

        return login;
    }
}
