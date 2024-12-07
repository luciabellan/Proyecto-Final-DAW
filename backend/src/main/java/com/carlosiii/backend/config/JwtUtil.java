package com.carlosiii.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component //Spring gestiona la clase y la inyecta donde sea necesaria
public class JwtUtil {

    private static final long EXPIRATION_TIME = 864_000_000; // Tiempo de expiración de los tokens (10 días)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //Clave secreta para firmar los tokens usando el algoritmo HS256
    //la clave se genera automáticamente al iniciar la aplicación. Al reiniciar la aplicación, se genera una nueva clave, invalidando los token anteriores.


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder() //genera un nuevo token JWT
                .setClaims(claims) //establece datos adicionales
                .setSubject(subject) //define el usuario (subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) //fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //fecha de expiración
                .signWith(key) //firma el token con la clave secreta
                .compact();
    }

    //Validación de tokens
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        //verifica que el username del token coincida con el proporcionado y que el token no haya expirado
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    //Extracción de información específica del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}