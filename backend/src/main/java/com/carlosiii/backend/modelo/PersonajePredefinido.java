package com.carlosiii.backend.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Entidad JPA que representa un personaje predefinido en el sistema
public class PersonajePredefinido {
    // Identificador único del personaje
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos  del personaje
    private String nombre;
    private String tipo; // Por ejemplo, "niño", "niña"
    private String colorPiel;
    private String colorPelo;
    private String formaPelo;
    private String imagenUrl;

    // Constructor vacío (requerido por JPA)
    public PersonajePredefinido() {
    }

    // Constructor con todos los campos
    public PersonajePredefinido(String nombre, String tipo, String colorPiel, String colorPelo, String formaPelo, String imagenUrl) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.colorPiel = colorPiel;
        this.colorPelo = colorPelo;
        this.formaPelo = formaPelo;
        this.imagenUrl = imagenUrl;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColorPiel() {
        return colorPiel;
    }

    public void setColorPiel(String colorPiel) {
        this.colorPiel = colorPiel;
    }

    public String getColorPelo() {
        return colorPelo;
    }

    public void setColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    public String getFormaPelo() {
        return formaPelo;
    }

    public void setFormaPelo(String formaPelo) {
        this.formaPelo = formaPelo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
