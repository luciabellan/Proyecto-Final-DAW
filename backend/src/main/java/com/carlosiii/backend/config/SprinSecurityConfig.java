package com.carlosiii.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //Indica que es una clase de seguridad de Spring
@EnableWebSecurity //Habilita la seguridad web de Spring Security
public class SprinSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { 
        //Utiliza BCrypct para el hash de contraseñas
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        //Gestiona el proceso de autenticación
        // Se utiliza para validar las credenciales de los usuarios
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        //Filtro para procesar los tokens JWT en las peticiones
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        //Esta es la configuración principal de seguridad
        return http
            .csrf(csrf -> csrf.disable()) //Desabilita CSRF (Cross-Site Request Forgery) ya que usamos JWT
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("**").permitAll() //permite acceso público a ciertos endpoints
                .requestMatchers("/api/cuentos-disponibles").authenticated() //requiere autenticación para /api/cuentos-disponibles
                .anyRequest().authenticated() //requiere autenticación para el resto de los endpoints
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //establece la política de sesiones como STATELESS (sin estado)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //añade el filtro JWT antes del filtro de autenticación estándar
            .build();
    }
}