package com.carlosiii.backend.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // Entidad JPA que representa un libro personalizado por un usuario
@Table(name = "libro_personalizado") // Nombre de la tabla en la base de datos
public class LibroPersonalizado {
    
    // Identificador único del libro personalizado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fecha y hora de creación del libro
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

     // Título del libro personalizado
    private String titulo;

    // Relación muchos a uno con CuentoDisponible (cuento base)
    @ManyToOne(fetch = FetchType.EAGER) // Carga inmediata de los datos del cuento
    @JoinColumn(name = "cuento_id") // Columna que establece la relación
    private CuentoDisponible cuento;

    // Relación muchos a uno con Usuario (creador del libro)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación uno a muchos con PersonajeCreado
    @JsonManagedReference // Manejo de serialización JSON para evitar ciclos infinitos
    @OneToMany(mappedBy = "libro",  // Campo en PersonajeCreado que mapea esta relación
    cascade = CascadeType.ALL, // Propaga operaciones CRUD a los personajes
    fetch = FetchType.EAGER, // Carga inmediata de los personajes
    orphanRemoval = true) // Elimina personajes huérfanos

    private List<PersonajeCreado> personajesCreados = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public LibroPersonalizado() {
    }

    // Getters y Setters para todos los atributos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CuentoDisponible getCuento() {
        return cuento;
    }

    public void setCuento(CuentoDisponible cuento) {
        this.cuento = cuento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PersonajeCreado> getPersonajesCreados() {
        return personajesCreados;
    }

    public void setPersonajesCreados(List<PersonajeCreado> personajesCreados) {
        this.personajesCreados = personajesCreados;
    }
}