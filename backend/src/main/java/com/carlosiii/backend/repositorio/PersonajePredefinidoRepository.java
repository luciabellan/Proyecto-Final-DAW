package com.carlosiii.backend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carlosiii.backend.modelo.PersonajePredefinido;

public interface PersonajePredefinidoRepository extends JpaRepository<PersonajePredefinido, Long> {
}
