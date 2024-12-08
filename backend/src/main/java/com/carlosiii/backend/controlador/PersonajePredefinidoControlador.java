package com.carlosiii.backend.controlador;

import com.carlosiii.backend.modelo.PersonajePredefinido;
import com.carlosiii.backend.servicio.PersonajePredefinidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //controlador REST para manejar peticiones HTTP
@RequestMapping("/api") //ruta base para todos los endpoints
public class PersonajePredefinidoControlador {

    // Inyección del servicio que maneja la lógica de personajes predefinidos
    @Autowired
    private PersonajePredefinidoServicio personajePredefinidoServicio;
   
   // Endpoint para obtener todos los personajes predefinidos
    @GetMapping("/personajes-predefinidos")// Mapea peticiones GET a esta ruta
    public ResponseEntity<List<PersonajePredefinido>> getPersonajesPredefinidos() {
        // Obtiene la lista de personajes a través del servicio
        List<PersonajePredefinido> personajes = personajePredefinidoServicio.obtenerTodos();
        // Retorna la lista con estado 200 OK
        return ResponseEntity.ok(personajes);
    }
}
