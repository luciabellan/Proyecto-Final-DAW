package com.carlosiii.backend.controlador;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
/* import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; */
import org.springframework.security.core.AuthenticationException;

import com.carlosiii.backend.config.JwtUtil;
import com.carlosiii.backend.dto.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;

@RestController //controlador REST
@RequestMapping("/api") //ruta base para todos los endpoints de este controlador

@CrossOrigin(origins = "https://proyecto-final-daw-production-5980.up.railway.app", allowCredentials = "true")
//CrossOrigin: especifica qué dominios pueden acceder a este controlador. AllowCredentials permite el envío de cookies y headers de autenticación
public class AuthControlador {

    //inyección de dependencias del JWUTil para generar tokens JWT
    @Autowired
    private JwtUtil jwtUtil;

    //este método maneja peticiones POST a /api/login
    @PostMapping("/login")
    // @RequestBody convierte el cuerpo de la petición JSON a un objeto LoginRequest
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            
            // Genera un token JWT usando el email del usuari
            final String jwt = jwtUtil.generateToken(loginRequest.getEmail());
            // Devuelve el token con estado 200 OK si todo es correcto
            return ResponseEntity.ok(jwt);
        } catch (AuthenticationException e) {
            // Devuelve error 401 Unauthorized si las credenciales son inválidas
            return ResponseEntity.status(401).body("Error: Credenciales inválidas");
        }
    }
}