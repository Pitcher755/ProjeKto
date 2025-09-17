package com.github.com.Pitcher755.projekto.view.auth;

import com.github.com.Pitcher755.projekto.dto.RegisterRequest;
import com.github.com.Pitcher755.projekto.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * Vista de registro de usuario.
 * Se encarga de recolectar datos y llamar a
 * {@link UserService#registerNewUser(RegisterRequest)}.
 */
@Route("register")
@PageTitle("Registro - Projekto")
public class RegisterView extends VerticalLayout {

    private final UserService userService;

    public RegisterView(UserService userService) {
        this.userService = userService;
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        add(createForm());
    }

    private FormLayout createForm() {
        TextField username = new TextField("Nombre de usuario");
        EmailField email = new EmailField("Email");
        PasswordField password = new PasswordField("Contraseña");
        PasswordField confirm = new PasswordField("Confirmar contraseña");

        Button registerButton = new Button("Registro");

        FormLayout layout = new FormLayout();
        layout.add(username, email, password, confirm, registerButton);
        layout.setMaxWidth("480px");

        Binder<RegisterRequest> binder = new Binder<>(RegisterRequest.class);

        // Bind properties simple
        binder.bind(username, RegisterRequest::getUsername, RegisterRequest::setUsername);
        binder.bind(email, RegisterRequest::getEmail, RegisterRequest::setEmail);
        binder.bind(password, RegisterRequest::getPassword, RegisterRequest::setPassword);

        // Acción del botón
        registerButton.addClickListener(ev -> {
            if (!password.getValue().equals(confirm.getValue())) {
                Notification.show("Contraseña incorrecta", 3000, Notification.Position.MIDDLE);
                return;
            }
            RegisterRequest dto = new RegisterRequest();
            dto.setUsername(username.getValue());
            dto.setEmail(email.getValue());
            dto.setPassword(password.getValue());
            dto.setRole("USER");

            try {
                userService.registerNewUser(dto);
                Notification.show("Registro completado con exito. Por favor, inicie sesión.", 3000,
                        Notification.Position.MIDDLE);
                // Navegar a login
                getUI().ifPresent(ui -> ui.navigate("login"));
            } catch (IllegalArgumentException ex) {
                Notification.show(ex.getMessage(), 4000, Notification.Position.MIDDLE);
            } catch (Exception ex) {
                Notification.show("Ha ocurrido un error. Intentelo de nuevo", 4000, Notification.Position.MIDDLE);
            }
        });

        return layout;
    }

}
