package com.carlosiii.backend.modelo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Entity // Entidad JPA que representa un usuario del sistema e implementa UserDetails para Spring Security
public class Usuario implements UserDetails {

    // Identificador único del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Datos del usuario
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String direccion;
    private String codigoPostal;

    // Constructor vacío (requerido por JPA)
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(String nombre, String apellidos, String email, String password, String direccion, String codigoPostal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

     // Implementación de métodos de UserDetails para Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna lista vacía de roles/autoridades
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
         // Usa el email como nombre de usuario
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cuenta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cuenta nunca se bloquea
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credenciales nunca expiran
    }

    @Override
    public boolean isEnabled() {
        return true; // Cuenta siempre activa
    }
}
