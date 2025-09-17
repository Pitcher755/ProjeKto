package com.github.com.Pitcher755.projekto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.com.Pitcher755.projekto.model.User;

/**
 * Repositorio JPS para la entidad {@link User}.
 * Provee búsquedas por username / email necesarias para autenticación y
 * registro.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username nombre de usuario
     * @return optiona con User si existe
     */
    Optional<User> findByUsername(String username);

    /**
     * Comprueba si un username ya está en uso.
     * 
     * @param username nombre de usuario
     * @return true si existe
     */
    boolean existsByUsername(String username);

    /**
     * Comprueba si un email ya estña en uso.
     * 
     * @param email email
     * @return true si existe
     */
    boolean existsByEmail(String email);
}
