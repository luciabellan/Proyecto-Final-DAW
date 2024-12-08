package com.carlosiii.backend.servicio;

import com.carlosiii.backend.modelo.CuentoDisponible;
import com.carlosiii.backend.repositorio.CuentoDisponibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Servicio que maneja la lógica de negocio relacionada con los cuentos disponibles
@Service // componente de servicio de Spring
public class CuentoDisponibleServicio {

    // Inyección del repositorio para acceder a la base de datos
    @Autowired
    private CuentoDisponibleRepository cuentoDisponibleRepository;

    // Método para obtener todos los cuentos disponibles
    public List<CuentoDisponible> obtenerTodos() {
        return cuentoDisponibleRepository.findAll();
    }

    // Método para obtener un cuento por su ID
    // Retorna Optional para manejar el caso de que no exista el cuento
    public Optional<CuentoDisponible> obtenerPorId(Long id) {
        return cuentoDisponibleRepository.findById(id);
    }

    // Verifica si un cuento corresponde al mes actual
    // Compara la descripción del cuento con el formato "mes año"
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
