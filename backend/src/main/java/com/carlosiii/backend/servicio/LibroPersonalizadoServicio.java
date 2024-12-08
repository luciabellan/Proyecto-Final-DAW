package com.carlosiii.backend.servicio;

import com.carlosiii.backend.modelo.LibroPersonalizado;
import com.carlosiii.backend.modelo.PersonajeCreado;
import com.carlosiii.backend.modelo.PersonajePredefinido;
import com.carlosiii.backend.modelo.Usuario;
import com.carlosiii.backend.modelo.CuentoDisponible;
import com.carlosiii.backend.repositorio.LibroPersonalizadoRepository;
import com.carlosiii.backend.repositorio.PersonajeCreadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

// Servicio que maneja la lógica de negocio para libros personalizados
@Service
public class LibroPersonalizadoServicio {

    // Inyección de dependencias necesarias
    @Autowired
    private LibroPersonalizadoRepository libroPersonalizadoRepository;

    @Autowired
    private PersonajeCreadoRepository personajeCreadoRepository;

    @Autowired
    private PersonajePredefinidoServicio personajePredefinidoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CuentoDisponibleServicio cuentoDisponibleServicio;

    // Obtener todos los libros de un usuario
    public List<LibroPersonalizado> obtenerLibrosPorUsuario(Long usuarioId) {
        return libroPersonalizadoRepository.findByUsuarioId(usuarioId);
    }

    // Obtiene los libros ordenados por fecha de creación descendente
    public List<LibroPersonalizado> obtenerLibrosDetalladosPorUsuario(Long usuarioId) {
        return libroPersonalizadoRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
    }

    // Crea un nuevo libro personalizado con sus personajes
    @Transactional // Asegura la integridad de la transacción
    public LibroPersonalizado crearLibro(Long usuarioId, Long cuentoId, List<PersonajeCreado> personajes) {
        // Verifica y obtiene el usuario y cuento
        Usuario usuario = usuarioServicio.obtenerPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CuentoDisponible cuento = cuentoDisponibleServicio.obtenerPorId(cuentoId)
                .orElseThrow(() -> new RuntimeException("Cuento no encontrado"));

        // Crea y configura el nuevo libro
        LibroPersonalizado libro = new LibroPersonalizado();
        libro.setUsuario(usuario);
        libro.setCuento(cuento);
        libro.setFechaCreacion(LocalDateTime.now());
        libro.setTitulo(cuento.getTitulo()); // Usamos el título del cuento base

        // Guardar el libro
        libro = libroPersonalizadoRepository.save(libro);

        // Procesa y guarda cada personaje del libro
        for (PersonajeCreado personaje : personajes) {
            // Establecer la relación con el libro
            personaje.setLibro(libro);

            // Buscar y establecer el personaje predefinido directamente
            // Aquí asumimos que el personajeId viene en el DTO
            Long personajeId = personaje.getPersonaje().getId();
            PersonajePredefinido personajePred = personajePredefinidoServicio.obtenerPorId(personajeId);
            if (personajePred == null) {
                throw new RuntimeException("Personaje predefinido no encontrado");
            }
            personaje.setPersonaje(personajePred);

            personajeCreadoRepository.save(personaje);
        }

        return libro;
    }

    // Obtener un libro específico por su ID
    public LibroPersonalizado obtenerLibro(Long id) {
        return libroPersonalizadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    // Verifica si un libro pertenece a un usuario específico
    public boolean perteneceAUsuario(Long libroId, Long usuarioId) {
        LibroPersonalizado libro = obtenerLibro(libroId);
        return libro.getUsuario().getId().equals(usuarioId);
    }
}