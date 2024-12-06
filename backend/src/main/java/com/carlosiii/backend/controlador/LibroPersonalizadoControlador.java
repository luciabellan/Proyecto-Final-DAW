package com.carlosiii.backend.controlador;

import com.carlosiii.backend.dto.LibroPersonalizadoDTO;
import com.carlosiii.backend.dto.LibroPersonalizadoResponseDTO;
import com.carlosiii.backend.modelo.LibroPersonalizado;
import com.carlosiii.backend.modelo.PersonajeCreado;
import com.carlosiii.backend.modelo.PersonajePredefinido;
import com.carlosiii.backend.modelo.Usuario;
import com.carlosiii.backend.servicio.LibroPersonalizadoServicio;
import com.carlosiii.backend.servicio.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LibroPersonalizadoControlador {

    @Autowired
    private LibroPersonalizadoServicio libroPersonalizadoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Crear nuevo libro personalizado
    @PostMapping("/libros-personalizados")
    public ResponseEntity<?> crearLibroPersonalizado(
            @RequestBody LibroPersonalizadoDTO libroDTO,
            Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            System.out.println("Email del usuario: " + userEmail);
            System.out.println("Datos recibidos: " + libroDTO);

            Usuario usuario = usuarioServicio.buscarPorEmail(userEmail);
            if (usuario == null) {
                return ResponseEntity.badRequest()
                        .body("Usuario no encontrado");
            }

            // Convertir DTO a entidades
            List<PersonajeCreado> personajes = libroDTO.getPersonajes().stream()
                    .map(personajeDTO -> {
                        PersonajeCreado personaje = new PersonajeCreado();
                        personaje.setPersonajeNombre(personajeDTO.getNombre());

                        // Crear y establecer el personaje predefinido
                        PersonajePredefinido personajePred = new PersonajePredefinido();
                        personajePred.setId(personajeDTO.getPersonajeId());
                        personaje.setPersonaje(personajePred);

                        return personaje;
                    })
                    .collect(Collectors.toList());

            LibroPersonalizado libro = libroPersonalizadoServicio.crearLibro(
                    usuario.getId(),
                    libroDTO.getCuentoId(),
                    personajes);

            return ResponseEntity.ok(libro);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("Error al crear el libro personalizado: " + e.getMessage());
        }
    }

    


    @GetMapping("/libros-personalizados")
    public ResponseEntity<?> obtenerLibrosUsuario(Authentication authentication) {
        try {
            // Obtener el email del token
            String userEmail = authentication.getName();
            
            // Obtener el usuario
            Usuario usuario = usuarioServicio.buscarPorEmail(userEmail);
            if (usuario == null) {
                return ResponseEntity.badRequest()
                    .body("Usuario no encontrado");
            }

            // Obtener los libros
            List<LibroPersonalizado> libros = 
                libroPersonalizadoServicio.obtenerLibrosDetalladosPorUsuario(usuario.getId());

             // Convertir las entidades a DTOs
            List<LibroPersonalizadoResponseDTO> librosDTO = libros.stream()
                .map(LibroPersonalizadoResponseDTO::fromEntity)
                .collect(Collectors.toList());

            System.out.println("Número de libros encontrados: " + librosDTO.size()); // Log para debug
            
            return ResponseEntity.ok(librosDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                .body("Error al obtener los libros personalizados: " + e.getMessage());
        }
    }


    // Obtener un libro específico
    @GetMapping("/libros-personalizados/{id}")
    public ResponseEntity<?> obtenerLibro(
            @PathVariable Long id,
            Authentication authentication) {
        try {
            Long usuarioId = Long.parseLong(authentication.getName());

            // Verificar que el libro pertenece al usuario
            if (!libroPersonalizadoServicio.perteneceAUsuario(id, usuarioId)) {
                return ResponseEntity.status(403).body("No tienes permiso para ver este libro");
            }

            LibroPersonalizado libro = libroPersonalizadoServicio.obtenerLibro(id);
            return ResponseEntity.ok(libro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el libro: " + e.getMessage());
        }
    }
}

// Clase para recibir los datos del request
class LibroPersonalizadoRequest {
    private Long cuentoId;
    private List<PersonajeCreado> personajes;

    // Getters y Setters
    public Long getCuentoId() {
        return cuentoId;
    }

    public void setCuentoId(Long cuentoId) {
        this.cuentoId = cuentoId;
    }

    public List<PersonajeCreado> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeCreado> personajes) {
        this.personajes = personajes;
    }
}