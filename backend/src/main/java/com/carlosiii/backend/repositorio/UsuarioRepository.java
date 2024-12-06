package com.carlosiii.backend.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import com.carlosiii.backend.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método de consulta para encontrar un usuario por su nombre de usuario
   
    
    boolean existsByEmail(String email);

     // Método para encontrar un usuario por email
     Usuario findByEmail(String email);

}
