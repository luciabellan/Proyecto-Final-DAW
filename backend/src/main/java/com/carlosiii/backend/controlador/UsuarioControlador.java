package com.carlosiii.backend.controlador;

import com.carlosiii.backend.modelo.Usuario;
import com.carlosiii.backend.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Controlador REST para manejar peticiones HTTP
@RequestMapping("/api") // Ruta base para todos los endpoints
@CrossOrigin(origins = "https://proyecto-final-daw-production-5980.up.railway.app", allowCredentials = "true") // Permite peticiones del frontend
public class UsuarioControlador {

    // Inyección del servicio que maneja la lógica de usuarios
    @Autowired
    private UsuarioServicio usuarioServicio;

    // Obtener lista de todos los usuarios
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioServicio.obtenerTodos();
    }

    // Obtener un usuario específico por su ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioServicio.obtenerPorId(id).orElse(null);
    }

    // Crear un nuevo usuario
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.guardarUsuario(usuario);
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioServicio.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(usuario);
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario( id);
    }

    // Obtener el perfil del usuario autenticado
    @GetMapping("/perfil")
    public ResponseEntity<Usuario> obtenerPerfil(Authentication authentication) {
        // Obtiene el email del usuario autenticado
        String email = authentication.getName();
        Usuario usuario = usuarioServicio.buscarPorEmail(email);
        if (usuario != null) {
            // Por seguridad, elimina la contraseña antes de enviar
            usuario.setPassword(null);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
