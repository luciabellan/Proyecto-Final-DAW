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
    
    // Para manejar todas las demás rutas de la SPA
    @RequestMapping(value = {
        "/{path:^(?!api|static|index\\.html|favicon\\.ico|.*\\..*).*$}/**"
    })
    public String forward() {
        return "forward:/index.html";
    }
}
