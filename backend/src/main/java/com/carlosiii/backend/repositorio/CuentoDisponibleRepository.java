package com.carlosiii.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlosiii.backend.modelo.CuentoDisponible;

public interface CuentoDisponibleRepository extends JpaRepository<CuentoDisponible, Long> {
}
