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

@Service
public class LibroPersonalizadoServicio {

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

    public List<LibroPersonalizado> obtenerLibrosDetalladosPorUsuario(Long usuarioId) {
        return libroPersonalizadoRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
    }

    // Crear un nuevo libro personalizado
    @Transactional
    public LibroPersonalizado crearLibro(Long usuarioId, Long cuentoId, List<PersonajeCreado> personajes) {
        // Obtener usuario y cuento
        Usuario usuario = usuarioServicio.obtenerPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        CuentoDisponible cuento = cuentoDisponibleServicio.  obtenerPorId(cuentoId)
                .orElseThrow(() -> new RuntimeException("Cuento no encontrado"));

        // Crear el libro
        LibroPersonalizado libro = new LibroPersonalizado();
        libro.setUsuario(usuario);
        libro.setCuento(cuento);
        libro.setFechaCreacion(LocalDateTime.now());
        libro.setTitulo(cuento.getTitulo()); // Usamos el título del cuento base

        // Guardar el libro
        libro = libroPersonalizadoRepository.save(libro);

        // Asociar los personajes al libro
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

    // Obtener un libro específico
    public LibroPersonalizado obtenerLibro(Long id) {
        return libroPersonalizadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    // Verificar si un libro pertenece a un usuario
    public boolean perteneceAUsuario(Long libroId, Long usuarioId) {
        LibroPersonalizado libro = obtenerLibro(libroId);
        return libro.getUsuario().getId().equals(usuarioId);
    }
}