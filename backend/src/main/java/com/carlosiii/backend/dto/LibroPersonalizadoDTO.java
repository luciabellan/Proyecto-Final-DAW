// LibroPersonalizadoDTO.java
package com.carlosiii.backend.dto;

import java.util.List;

public class LibroPersonalizadoDTO {
    private Long cuentoId;
    private List<PersonajeDTO> personajes;

    // Clase interna para los datos del personaje
    public static class PersonajeDTO {
        private Long personajeId;
        private String nombre;
        private String tipo;

        // Getters y Setters
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

    // Getters y Setters para LibroPersonalizadoDTO
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