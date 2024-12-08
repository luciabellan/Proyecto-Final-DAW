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

@RestController //REST
@RequestMapping("/api") //ruta base
public class LibroPersonalizadoControlador {

    //Servicio inyectado para operaciones con libros
    @Autowired
    private LibroPersonalizadoServicio libroPersonalizadoServicio;

    //Servicio inyectado para operaciones con usuarios
    @Autowired
    private UsuarioServicio usuarioServicio;

    // Crear nuevo libro personalizado
    @PostMapping("/libros-personalizados")
    public ResponseEntity<?> crearLibroPersonalizado(
            @RequestBody LibroPersonalizadoDTO libroDTO, //recibe los datos del libro
            Authentication authentication) {// Objeto que contiene la información del usuario autenticado
        try {
            // Obtiene el email del usuario autenticado
            String userEmail = authentication.getName();
            System.out.println("Email del usuario: " + userEmail);
            System.out.println("Datos recibidos: " + libroDTO);

            // Busca el usuario en la base de datos
            Usuario usuario = usuarioServicio.buscarPorEmail(userEmail);
            if (usuario == null) {
                return ResponseEntity.badRequest()
                        .body("Usuario no encontrado");
            }

            // Convierte la lista de personajes del DTO a entidades
            List<PersonajeCreado> personajes = libroDTO.getPersonajes().stream()
                    .map(personajeDTO -> {
                         // Crea nuevo personaje
                        PersonajeCreado personaje = new PersonajeCreado();
                        personaje.setPersonajeNombre(personajeDTO.getNombre());

                        // Crear y establecer el personaje predefinido
                        PersonajePredefinido personajePred = new PersonajePredefinido();
                        personajePred.setId(personajeDTO.getPersonajeId());
                        personaje.setPersonaje(personajePred);

                        return personaje;
                    })
                    .collect(Collectors.toList());

            // Crea el libro usando el servicio
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


    // Endpoint para obtener todos los libros de un usuario
    @GetMapping("/libros-personalizados")
    public ResponseEntity<?> obtenerLibrosUsuario(Authentication authentication) {
        try {
            // Obtiene el email del usuario del token de autenticación
            String userEmail = authentication.getName();
            
            // Busca el usuario en la base de datos
            Usuario usuario = usuarioServicio.buscarPorEmail(userEmail);
            if (usuario == null) {
                return ResponseEntity.badRequest()
                    .body("Usuario no encontrado");
            }

            // Obtiene los libros del usuario
            List<LibroPersonalizado> libros = 
                libroPersonalizadoServicio.obtenerLibrosDetalladosPorUsuario(usuario.getId());

            // Convierte las entidades a DTOs para la respuesta
            List<LibroPersonalizadoResponseDTO> librosDTO = libros.stream()
                .map(LibroPersonalizadoResponseDTO::fromEntity)
                .collect(Collectors.toList());

            System.out.println("Número de libros encontrados: " + librosDTO.size()); // Log para debug
            
             // Devuelve la lista de libros
            return ResponseEntity.ok(librosDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                .body("Error al obtener los libros personalizados: " + e.getMessage());
        }
    }


    // Endpoint para obtener un libro específico por ID
    @GetMapping("/libros-personalizados/{id}")
    public ResponseEntity<?> obtenerLibro(
            @PathVariable Long id, // ID del libro a obtener
            Authentication authentication) {
        try {
            Long usuarioId = Long.parseLong(authentication.getName());

            // Verifica que el usuario tenga permiso para ver este libro
            if (!libroPersonalizadoServicio.perteneceAUsuario(id, usuarioId)) {
                return ResponseEntity.status(403).body("No tienes permiso para ver este libro");
            }
            // Obtiene y retorna el libro
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