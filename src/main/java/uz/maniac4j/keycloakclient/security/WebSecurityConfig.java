package uz.maniac4j.keycloakclient.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth->auth.requestMatchers( "/test/anonymous", "/test/anonymous/**").permitAll()
                .requestMatchers("/test/admin", "/test/admin/**").hasRole(ADMIN)
                .requestMatchers("/test/user").hasAnyRole(ADMIN, USER)
                .anyRequest().authenticated());

        http.oauth2ResourceServer(oauth2->oauth2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)));

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        return http.build();
    }

}