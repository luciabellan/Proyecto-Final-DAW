package com.carlosiii.backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosiii.backend.modelo.PersonajeCreado;

@Repository // Repositorio para gestionar las operaciones con PersonajeCreado en la base de
            // datos
public interface PersonajeCreadoRepository extends JpaRepository<PersonajeCreado, Long> {
    // Busca todos los personajes que pertenecen a un libro específico
    List<PersonajeCreado> findByLibroId(Long libroId);

    // Elimina todos los personajes asociados a un libro específico
    void deleteByLibroId(Long libroId);
    // Hereda los métodos CRUD básicos de JpaRepository:
    // - save(PersonajeCreado)
    // - findById(Long)
    // - findAll()
    // - delete(PersonajeCreado)

}
