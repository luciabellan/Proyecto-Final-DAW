package com.carlosiii.backend.servicio;

import com.carlosiii.backend.modelo.PersonajePredefinido;
import com.carlosiii.backend.repositorio.PersonajePredefinidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajePredefinidoServicio {
    @Autowired
    private PersonajePredefinidoRepository personajePredefinidoRepository;

    // Método para obtener todos los personajes predefinidos
    public List<PersonajePredefinido> obtenerTodos() {
        return personajePredefinidoRepository.findAll();
    }


    // Nuevo método para obtener por ID
    public PersonajePredefinido obtenerPorId(Long id) {
        return personajePredefinidoRepository.findById(id).orElse(null);
    }

    // Verificar si un personaje es del tipo especificado
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
