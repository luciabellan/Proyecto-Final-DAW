package com.carlosiii.backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosiii.backend.modelo.LibroPersonalizado;

@Repository
public interface LibroPersonalizadoRepository extends JpaRepository<LibroPersonalizado, Long> {
    List<LibroPersonalizado> findByUsuarioId(Long usuarioId);

     // Ordenado por fecha de creación
     List<LibroPersonalizado> findByUsuarioIdOrderByFechaCreacionDesc(Long usuarioId);

}
