package com.carlosiii.backend.servicio;

import com.carlosiii.backend.modelo.PersonajePredefinido;
import com.carlosiii.backend.repositorio.PersonajePredefinidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// Servicio que maneja la lógica de negocio para los personajes predefinidos del sistema
@Service
public class PersonajePredefinidoServicio {

    // Inyección del repositorio para acceder a la base de datos
    @Autowired
    private PersonajePredefinidoRepository personajePredefinidoRepository;

    // Obtiene la lista completa de personajes predefinidos disponibles
    public List<PersonajePredefinido> obtenerTodos() {
        return personajePredefinidoRepository.findAll();
    }


    // Busca y retorna un personaje predefinido por su ID
   // Si no existe, retorna null
    public PersonajePredefinido obtenerPorId(Long id) {
        return personajePredefinidoRepository.findById(id).orElse(null);
    }

    // Verifica si un personaje corresponde al mes actual
   // Compara la descripción con el formato "mes año"
    public boolean esCuentoMesActual(String descripcion) {
        // Obtener mes y año actual en formato "mes año" (ej: "agosto 2024")
        java.time.LocalDate fecha = java.time.LocalDate.now();
        String mesActual = fecha.getMonth().toString().toLowerCase();
        String anoActual = String.valueOf(fecha.getYear());
        String mesAnoActual = mesActual + " " + anoActual;
        
        // Compara ignorando mayúsculas/minúsculas
        return descripcion.toLowerCase().equals(mesAnoActual);
    }

}
