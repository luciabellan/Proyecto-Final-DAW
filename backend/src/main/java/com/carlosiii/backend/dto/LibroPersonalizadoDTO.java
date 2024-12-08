// LibroPersonalizadoDTO.java
package com.carlosiii.backend.dto;

import java.util.List;

// Clase DTO para transferir datos de libros personalizados entre cliente y servidor
public class LibroPersonalizadoDTO {

    // ID del cuento base que se va a personalizar
    private Long cuentoId;
    // Lista de personajes que se incluirán en el libro
    private List<PersonajeDTO> personajes;

     // Clase interna que define la estructura de datos para cada personaje
    public static class PersonajeDTO {
        private Long personajeId; // ID del personaje predefinido base
        private String nombre;    // Nombre personalizado del personaje
        private String tipo;      // Tipo de personaje (niño/niña)

        // Getters y Setters para acceder a los datos del personaje
        public Long getPersonajeId() {
            return personajeId;
        }

        public void setPersonajeId(Long personajeId) {
            this.personajeId = personajeId;
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
    }

    // Getters y Setters para acceder a los datos del libro
    public Long getCuentoId() {
        return cuentoId;
    }

    public void setCuentoId(Long cuentoId) {
        this.cuentoId = cuentoId;
    }

    public List<PersonajeDTO> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeDTO> personajes) {
        this.personajes = personajes;
    }
}