package com.github.com.Pitcher755.projekto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.github.com.Pitcher755.projekto.security.CustomUserDetailsService;

/**
 * Configuración de seguridad para la aplicación.
 * 
 * Notas:
 * - Permitidos acceso a rutas públicas (/login, /register, /h2-console y
 * recursos Vaadin).
 * - Protegemos el acceso de rutas.
 * - Usamos DAO provider con {@link CustomUserDetailsService} y
 * {@link BCryptPasswordEncoder}.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Password encoder - usar BCrypt para el hashing de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager - expuesto para permitir autenticación programática si
     * se necesita.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * DaoAuthenticationProvider configurado con nuestro UserDetailsService.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider prov = new DaoAuthenticationProvider(userDetailsService);
        prov.setPasswordEncoder(passwordEncoder());
        return prov;
    }

    /**
     * Filtro de seguridad principal.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Deshabilitamos CSRF para simplificar desarrollo (se puede activar
        // posteriormente)
        http.csrf(csrf -> csrf.disable());

        // Permitir frames desde el mismo origen (necesario para H2 console).
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        // Rutas públicas (login, register, h2 console, y recursos estáticos de Vaadin).
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login",
                        "/register",
                        "/h2-console/**",
                        "/favivon.ico",
                        "/VAADIN/**",
                        "/frontend/**",
                        "/frontend-es5/**",
                        "/sw.js",
                        "/web-app/**",
                        "/webjars/**",
                        "/icons/**",
                        "/images/**",
                        "/styles/**")
                .permitAll()
                .anyRequest()
                .authenticated());

        // Usamos el login form de Spring Security (la vista /login la proveeremos con
        // Vaadin)
        http.formLogin(form -> form.loginPage("/login").permitAll());

        // Logout
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

        return http.build();
    }
}
