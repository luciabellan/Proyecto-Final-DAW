package com.carlosiii.backend.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CuentoDisponible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descripcion;
    private String imagen_url;
    private String titulo;

    // Constructor vacío (requerido por JPA)
    public CuentoDisponible() {
    }

    // Constructor con todos los campos
    public CuentoDisponible(String titulo, String descripcion, String imagen_url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen_url = imagen_url;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagen_url;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagen_url = imagenUrl;
    }
}
