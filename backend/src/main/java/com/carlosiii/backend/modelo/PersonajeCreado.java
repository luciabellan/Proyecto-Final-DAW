package com.carlosiii.backend.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // Entidad JPA que representa un personaje personalizado dentro de un libro
@Table(name = "personaje_creado")
public class PersonajeCreado {

    // Identificador único del personaje creado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Relación muchos a uno con LibroPersonalizado
    @JsonBackReference // Evita ciclos infinitos en la serialización JSON
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private LibroPersonalizado libro;

    // Relación muchos a uno con PersonajePredefinido (personaje base)
    @ManyToOne
    @JoinColumn(name = "personaje_id")
    private PersonajePredefinido personaje;

    // Nombre personalizado del personaje
    @Column(name = "personaje_nombre")
    private String personajeNombre;

    // Constructor vacío requerido por JPA
    public PersonajeCreado() {
    }

    // Getters y Setters para acceder y modificar los atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibroPersonalizado getLibro() {
        return libro;
    }

    public void setLibro(LibroPersonalizado libro) {
        this.libro = libro;
    }

    public PersonajePredefinido getPersonaje() {
        return personaje;
    }

    public void setPersonaje(PersonajePredefinido personaje) {
        this.personaje = personaje;
    }

    public String getPersonajeNombre() {
        return personajeNombre;
    }

    public void setPersonajeNombre(String personajeNombre) {
        this.personajeNombre = personajeNombre;
    }
}
