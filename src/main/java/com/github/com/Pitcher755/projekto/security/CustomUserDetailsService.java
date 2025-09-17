package com.github.com.Pitcher755.projekto.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.github.com.Pitcher755.projekto.model.User;
import com.github.com.Pitcher755.projekto.repository.UserRepository;

/**
 * Servicio que carga UserDetails a partir de {@link UserRepository}.
 * Spring Security usará esta implementación para autenticar usuarios.
 */
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca un usuario por username y lo adapta a {@link CustomUserDetails}.
     * 
     * @param username nombre de usuario
     * @return UserDetails
     * @throws UsernameNotFoundException si no existe
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return new CustomUserDetails(user);
    }
}
