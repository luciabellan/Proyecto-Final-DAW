package com.carlosiii.backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosiii.backend.modelo.PersonajeCreado;

@Repository
public interface PersonajeCreadoRepository extends JpaRepository<PersonajeCreado, Long> {
       // Método para encontrar todos los personajes creados asociados a un libro personalizado
    List<PersonajeCreado> findByLibroId(Long libroId);

    // Método para eliminar todos los personajes creados asociados a un libro personalizado
    void deleteByLibroId(Long libroId);

    
}
