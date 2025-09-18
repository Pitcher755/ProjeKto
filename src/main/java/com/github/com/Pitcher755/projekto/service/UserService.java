package com.github.com.Pitcher755.projekto.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.com.Pitcher755.projekto.dto.RegisterRequest;
import com.github.com.Pitcher755.projekto.model.Role;
import com.github.com.Pitcher755.projekto.model.User;
import com.github.com.Pitcher755.projekto.repository.UserRepository;

/**
 * Servicio para operaciones relacionadas con usuarios:
 * - registro
 * - búsquedas
 * - helpers para inicialización
 * 
 * Toda la lógica de persistencia pasa por aquí para mantener las
 * vistas/controller
 * ligeras y testeables.
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuevo usuario a partir del DTO de registro.
     * 
     * @param req datos del registro (username, email, password, role)
     * @return usuario persistido
     * @throws IllegalArgumentException si username o email ya existen
     */
    public User registerNewUser(RegisterRequest req) {
        // Validaciones básicas
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario está en uso");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Este email esta en uso.");
        }

        // Mapear rol por defecto
        Role role;
        try {
            role = Role.valueOf(req.getRole().toUpperCase());
        } catch (Exception ex) {
            role = Role.USER;
        }

        // Crear entidad y hashear contraseña
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(role);
        user.setEnabled(true);

        // Persistir
        return userRepository.save(user);

    }

    /**
     * Busca usuario por username.
     * 
     * @param username nombre de usuario
     * @return optional con User
     */
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Crea un usuario administrativo si no existe (útil para dev).
     * 
     * @param username    admin username
     * @param email       admin email
     * @param rawPassword constraseña en claro (se guarda encriptada)
     * @return usuario creado o existente
     */
    public User createAdminIfNotExists(String username, String email, String rawPassword) {
        Optional<User> existing = userRepository.findByUsername(username);
        if (existing.isPresent()) {
            return existing.get();
        }

        User admin = new User();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(rawPassword));
        admin.setRole(Role.ADMIN);
        admin.setEnabled(true);
        return userRepository.save(admin);

    }
}
