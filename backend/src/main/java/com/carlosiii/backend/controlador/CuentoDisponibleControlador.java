package com.carlosiii.backend.controlador;

import com.carlosiii.backend.modelo.CuentoDisponible;
import com.carlosiii.backend.servicio.CuentoDisponibleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") 
public class CuentoDisponibleControlador {

    @Autowired
    private CuentoDisponibleServicio cuentoDisponibleServicio;

    // Obtener todos los cuentos disponibles
    @GetMapping("/cuentos-disponibles")
        @PreAuthorize("hasRole('USER')") // solo usuarios autenticados pueden acceder

   public ResponseEntity<?> getCuentosDisponibles() {
        try {
            List<CuentoDisponible> cuentos = cuentoDisponibleServicio.obtenerTodos();
            System.out.println("Cuentos encontrados: " + cuentos.size()); // Debug
            return ResponseEntity.ok(cuentos);
        } catch (Exception e) {
            System.err.println("Error al obtener cuentos: " + e.getMessage());
            e.printStackTrace(); // Esto mostrará el stack trace en los logs del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error al obtener cuentos: " + e.getMessage());
        }
    }

    
}
