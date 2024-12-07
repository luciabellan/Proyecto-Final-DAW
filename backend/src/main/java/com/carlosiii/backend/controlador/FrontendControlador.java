package com.carlosiii.backend.controlador;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendControlador {

    @RequestMapping(value = "/")
    public String index() {
        return "forward:/index.html";
    }
    
    // // Maneja todas las rutas del frontend excepto las específicas
    @RequestMapping(value = {
        //excluye: Rutas que empiezan con /api, Archivos estáticos, El propio index.html, favicon.ico, Archivos con extensión
        "/{path:^(?!api|static|index\\.html|favicon\\.ico|.*\\..*).*$}/**"
    })
    public String forward() {
        //dirige todas las rutas del frontend al index.html
        return "forward:/index.html";
    }
}
