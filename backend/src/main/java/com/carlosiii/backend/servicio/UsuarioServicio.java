package com.carlosiii.backend.servicio;

import com.carlosiii.backend.modelo.LibroPersonalizado;
import com.carlosiii.backend.modelo.Usuario;
import com.carlosiii.backend.repositorio.LibroPersonalizadoRepository;
import com.carlosiii.backend.repositorio.PersonajeCreadoRepository;
import com.carlosiii.backend.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Servicio que maneja la lógica de negocio para usuarios e implementa UserDetailsService para autenticación
@Service
public class UsuarioServicio implements UserDetailsService {

    // Inyección de dependencias
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroPersonalizadoRepository libroPersonalizadoRepository;

    @Autowired
    private PersonajeCreadoRepository personajeCreadoRepository;

    // Inyección del codificador de contraseñas
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicio(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario getUserByUsername(String nombre) {
        return usuarioRepository.findByEmail(nombre);
    }

    // Guarda un nuevo usuario, encriptando su contraseña
    public Usuario guardarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    // Método para obtener un usuario por ID
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Elimina un usuario y todos sus datos relacionados
    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Elimina los libros y personajes del usuario
        List<LibroPersonalizado> libros = libroPersonalizadoRepository.findByUsuarioId(id);
        for (LibroPersonalizado libro : libros) {
            // Eliminar los personajes asociados a cada libro
            personajeCreadoRepository.deleteByLibroId(libro.getId());
        }
        libroPersonalizadoRepository.deleteAll(libros);

        // Eliminar el usuario
        usuarioRepository.delete(usuario);
    }

    public boolean validarPassword(String passwordIngresada, String passwordGuardada) {
        // Aquí podrías agregar lógica para encriptación o simplemente comparar las
        // contraseñas
        return passwordIngresada.equals(passwordGuardada);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Implementación de UserDetailsService para autenticación
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario;
    }

    // Método para actualizar un usuario
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        // Buscar el usuario por ID en la base de datos
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar solo los campos necesarios
        if (usuarioActualizado.getNombre() != null && !usuarioActualizado.getNombre().isEmpty()) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
        }
        if (usuarioActualizado.getEmail() != null && !usuarioActualizado.getEmail().isEmpty()) {
            if (!usuarioExistente.getEmail().equals(usuarioActualizado.getEmail())) {
                // Verificar que el nuevo email no esté en uso por otro usuario
                if (usuarioRepository.existsByEmail(usuarioActualizado.getEmail())) {
                    throw new RuntimeException("El correo electrónico ya está en uso");
                }
                usuarioExistente.setEmail(usuarioActualizado.getEmail());
            }
        }
        if (usuarioActualizado.getDireccion() != null && !usuarioActualizado.getDireccion().isEmpty()) {
            usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
        }
        if (usuarioActualizado.getCodigoPostal() != null && !usuarioActualizado.getCodigoPostal().isEmpty()) {
            usuarioExistente.setCodigoPostal(usuarioActualizado.getCodigoPostal());
        }

        // Si se pasa una contraseña nueva, actualizarla
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }

        // Guardar y devolver el usuario actualizado
        return usuarioRepository.save(usuarioExistente);
    }

}
