package com.carlosiii.backend.servicio;

import com.carlosiii.backend.modelo.CuentoDisponible;
import com.carlosiii.backend.repositorio.CuentoDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentoDisponibleServicio {
    @Autowired
    private CuentoDisponibleRepository cuentoDisponibleRepository;

    // Método para obtener todos los cuentos disponibles
    public List<CuentoDisponible> obtenerTodos() {
        return cuentoDisponibleRepository.findAll();
    }


    // Método para obtener un cuento por su ID
    public Optional<CuentoDisponible> obtenerPorId(Long id) {
        return cuentoDisponibleRepository.findById(id);
    }

     // Verificar si un cuento es el del mes actual
     public boolean esCuentoMesActual(String descripcion) {
        // Obtener mes y año actual en formato "mes año" (ej: "agosto 2024")
        java.time.LocalDate fecha = java.time.LocalDate.now();
        String mesActual = fecha.getMonth().toString().toLowerCase();
        String anoActual = String.valueOf(fecha.getYear());
        String mesAnoActual = mesActual + " " + anoActual;
        
        // Comparar con la descripción del cuento
        return descripcion.toLowerCase().equals(mesAnoActual);
    }

}
