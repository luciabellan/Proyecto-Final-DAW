package com.carlosiii.backend.controlador;

import com.carlosiii.backend.modelo.CuentoDisponible;
import com.carlosiii.backend.servicio.CuentoDisponibleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //controlador REST
@RequestMapping("/api")  // Ruta base
@CrossOrigin(origins = "https://proyecto-final-daw-production-5980.up.railway.app") 
public class CuentoDisponibleControlador {

    // Inyección del servicio que maneja la lógica de negocio
    @Autowired
    private CuentoDisponibleServicio cuentoDisponibleServicio;

    // Endpoint para obtener todos los cuentos disponibles
    @GetMapping("/cuentos-disponibles")
        @PreAuthorize("hasRole('USER')") // solo usuarios autenticados pueden acceder

   public ResponseEntity<?> getCuentosDisponibles() {
        try {
            // Obtiene la lista de cuentos a través del servicio
            List<CuentoDisponible> cuentos = cuentoDisponibleServicio.obtenerTodos();
            System.out.println("Cuentos encontrados: " + cuentos.size()); // Debug
            return ResponseEntity.ok(cuentos); //200 ok

        } catch (Exception e) {
            System.err.println("Error al obtener cuentos: " + e.getMessage());
            e.printStackTrace(); // Esto mostrará el stack trace en los logs del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error al obtener cuentos: " + e.getMessage());
        }
    }

    
}
