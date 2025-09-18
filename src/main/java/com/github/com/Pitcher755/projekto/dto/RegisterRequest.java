package com.github.com.Pitcher755.projekto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para los datos de registro de usuario.
 * Usamos validaciones bean-validation para integridad en el servicio /
 * controlador.
 */
public class RegisterRequest {

    @NotBlank(message = "Nombre de usuario es requerido")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Email es requerido")
    @Email
    private String email;

    @NotBlank(message = "La constraseña es requerida")
    @Size(min = 6, max = 100)
    private String password;

    // Optional: rol inicial, por defecto USER
    private String role = "USER";

    // Constructores

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        if (role != null)
            this.role = role;
    }

    // Getters y Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
