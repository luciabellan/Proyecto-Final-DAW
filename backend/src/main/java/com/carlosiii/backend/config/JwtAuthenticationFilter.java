package com.carlosiii.backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //OncePerRequestFilter garantiza que el filtro se ejecute una sola vez por cada petición HTTP

    //Dependencias inyectadas
    @Autowired
    private JwtUtil jwtUtil; //para manejar operaciones con JWT

    @Autowired
    private UserDetailsService userDetailsService; //para cargar los detalles del usuario desde la base de datos

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
    //doFilterInternal se ejecuta en cada petición        
    throws ServletException, IOException {
        
        //Extracción del Token
        final String authorizationHeader = request.getHeader("Authorization"); //Verifica si existe el header 'Authorization'
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { //comprueba si comienza con 'Bearer'
            jwt = authorizationHeader.substring(7); //extrae el token
            username = jwtUtil.extractUsername(jwt); //extrae el username
        }
        //Validación y autenticación
        //username extraido del token y que no haya una autenticación ya existente
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //Carga los detalles completos del usuario desde la base de datos
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            
            //Valida que el token sea válido para el usuario y que corresponda al username
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {

                //Crea un objeto de autenticación de Spring Security
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                //Añade detalles de la petición HTTP 
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //establece la autenticación en el contexto de la seguridad
                //el usuario queda autenticado para el resto de la petición
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        //continua la cadena de filtros. Permite que la petición continúe al controlador
        filterChain.doFilter(request, response);
    }
}