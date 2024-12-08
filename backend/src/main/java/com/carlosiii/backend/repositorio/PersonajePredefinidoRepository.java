package com.carlosiii.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosiii.backend.modelo.PersonajePredefinido;

@Repository // Repositorio para gestionar las operaciones con PersonajePredefinido en la base de datos
public interface PersonajePredefinidoRepository extends JpaRepository<PersonajePredefinido, Long> {
    // Hereda todos los métodos CRUD básicos de JpaRepository:
   // - save(PersonajePredefinido): Guardar un personaje predefinido
   // - findById(Long): Buscar un personaje por ID
   // - findAll(): Obtener todos los personajes predefinidos
   // - delete(PersonajePredefinido): Eliminar un personaje
}
