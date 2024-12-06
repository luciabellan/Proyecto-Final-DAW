package com.carlosiii.backend.controlador;

import com.carlosiii.backend.modelo.PersonajePredefinido;
import com.carlosiii.backend.servicio.PersonajePredefinidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonajePredefinidoControlador {

    @Autowired
    private PersonajePredefinidoServicio personajePredefinidoServicio;

    // Obtener todos los personajes predefinidos
    @GetMapping("/personajes-predefinidos")
    public ResponseEntity<List<PersonajePredefinido>> getPersonajesPredefinidos() {
        List<PersonajePredefinido> personajes = personajePredefinidoServicio.obtenerTodos();
        return ResponseEntity.ok(personajes);
    }
}
