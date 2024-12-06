package com.carlosiii.backend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Redirige todas las rutas no manejadas al index.html de React
        return "forward:/index.html";
    }
}
