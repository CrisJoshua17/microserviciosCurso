package org.aguzman.springcloud.msvc.usuarios;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
public class SecurityConfig {
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/authorized").permitAll()
                                .antMatchers(HttpMethod.GET, "/")
                                .hasAnyAuthority("SCOPE_read", "SCOPE_write")
                                .antMatchers(HttpMethod.POST, "/").hasAuthority("SCOPE_write")
                                .antMatchers(HttpMethod.PUT, "/{id}").hasAuthority("SCOPE_write")
                                .antMatchers(HttpMethod.DELETE, "/{id}").hasAuthority("SCOPE_write")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/oauth2/authorization/mscv-usuarios-client")
                )
                .oauth2Client(withDefaults()).oauth2ResourceServer(server -> server.jwt());
    
        return http.build();
    }
}
