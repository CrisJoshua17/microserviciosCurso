package org.joshua.springcloud.mscv.oauth.Services;

import java.util.Collections;

import org.joshua.springcloud.mscv.oauth.Models.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Service
public class UsuarioService implements UserDetailsService {

    private final WebClient.Builder webClient;
    private final PasswordEncoder passwordEncoder;

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    public UsuarioService(WebClient.Builder webClientBuilder, PasswordEncoder passwordEncoder) {
        this.webClient = (Builder) webClientBuilder.baseUrl("http://msvc-usuarios").build();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Usuario usuario = webClient.build().get()
                    .uri(uriBuilder -> uriBuilder.path("/login")
                            .queryParam("email", email)
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Usuario.class)
                    .block();

            if (usuario == null) {
                throw new UsernameNotFoundException("Error en el login, no existe el usuario '" + email + "' en el sistema");
            }

            log.info("Usuario login: " + usuario.getEmail());
            log.info("Usuario login: " + usuario.getNombre());
            log.info("Usuario login: " + usuario.getPassword());

            return new User(email, passwordEncoder.encode(usuario.getPassword()), true, true, true, true,
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } catch (RuntimeException e) {
            String error = "Error en el login, no existe el usuario '" + email + "' en el sistema";
            log.error(error);
            log.error(e.getMessage());
            throw new UsernameNotFoundException(error);
        }
    }
}