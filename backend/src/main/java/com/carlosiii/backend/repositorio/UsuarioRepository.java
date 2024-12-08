package com.carlosiii.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosiii.backend.modelo.Usuario;

@Repository // Repositorio para gestionar las operaciones con usuarios en la base de datos
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /// Verifica si existe un usuario con el email especificado
    boolean existsByEmail(String email);

    // Busca y retorna un usuario por su email
    // Este método es crucial para la autenticación ya que el email se usa como
    // username
    Usuario findByEmail(String email);
    // Hereda los métodos CRUD básicos de JpaRepository:
    // - save(Usuario): Guardar un usuario
    // - findById(Long): Buscar usuario por ID
    // - findAll(): Obtener todos los usuarios
    // - delete(Usuario): Eliminar un usuario

}
