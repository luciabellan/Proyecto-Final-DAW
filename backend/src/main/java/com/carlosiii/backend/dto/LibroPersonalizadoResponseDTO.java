package com.carlosiii.backend.dto;

import com.carlosiii.backend.modelo.LibroPersonalizado;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LibroPersonalizadoResponseDTO {
    private Long id;
    private String titulo;
    private LocalDateTime fechaCreacion;
    private String cuentoTitulo;
    private String cuentoImagenUrl;
    private List<PersonajeResponseDTO> personajes;

    // DTO interno para los personajes
    public static class PersonajeResponseDTO {
        private Long id;
        private String personajeNombre;

        // Getters y Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPersonajeNombre() {
            return personajeNombre;
        }

        public void setPersonajeNombre(String personajeNombre) {
            this.personajeNombre = personajeNombre;
        }
    }

    // Método para convertir de Entidad a DTO
    public static LibroPersonalizadoResponseDTO fromEntity(LibroPersonalizado libro) {
        LibroPersonalizadoResponseDTO dto = new LibroPersonalizadoResponseDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setFechaCreacion(libro.getFechaCreacion());
        dto.setCuentoTitulo(libro.getCuento().getTitulo());
        dto.setCuentoImagenUrl(libro.getCuento().getImagenUrl());
        
        List<PersonajeResponseDTO> personajesDTO = libro.getPersonajesCreados().stream()
            .map(p -> {
                PersonajeResponseDTO pDTO = new PersonajeResponseDTO();
                pDTO.setId(p.getId());
                pDTO.setPersonajeNombre(p.getPersonajeNombre());
                return pDTO;
            })
            .collect(Collectors.toList());
        
        dto.setPersonajes(personajesDTO);
        return dto;
    }

    // Getters y Setters
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCuentoTitulo() {
        return cuentoTitulo;
    }

    public void setCuentoTitulo(String cuentoTitulo) {
        this.cuentoTitulo = cuentoTitulo;
    }

    public String getCuentoImagenUrl() {
        return cuentoImagenUrl;
    }

    public void setCuentoImagenUrl(String cuentoImagenUrl) {
        this.cuentoImagenUrl = cuentoImagenUrl;
    }

    public List<PersonajeResponseDTO> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeResponseDTO> personajes) {
        this.personajes = personajes;
    }
}