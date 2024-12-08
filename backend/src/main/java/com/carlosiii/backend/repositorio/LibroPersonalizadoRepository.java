package com.carlosiii.backend.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.carlosiii.backend.modelo.LibroPersonalizado;

// Repositorio para gestionar las operaciones de base de datos de LibroPersonalizado
@Repository // Indica que es un componente de repositorio de Spring
public interface LibroPersonalizadoRepository extends JpaRepository<LibroPersonalizado, Long> {
    // Método personalizado para encontrar todos los libros de un usuario específico
    List<LibroPersonalizado> findByUsuarioId(Long usuarioId);

    // Método para obtener los libros de un usuario ordenados por fecha de creación
    // descendente
    List<LibroPersonalizado> findByUsuarioIdOrderByFechaCreacionDesc(Long usuarioId);

    // Hereda métodos CRUD estándar de JpaRepository:
    // - save()
    // - findById()
    // - findAll()
    // - delete()

}
