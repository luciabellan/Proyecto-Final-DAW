package com.carlosiii.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlosiii.backend.modelo.CuentoDisponible;

public interface CuentoDisponibleRepository extends JpaRepository<CuentoDisponible, Long> {
    // Hereda todos los métodos CRUD básicos de JpaRepository:
   // - save(CuentoDisponible): Guardar un cuento
   // - findById(Long): Buscar un cuento por ID
   // - findAll(): Obtener todos los cuentos
   // - delete(CuentoDisponible): Eliminar un cuento

}
