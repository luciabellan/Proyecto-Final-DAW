package com.carlosiii.backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        //mapeo de rutas
        //Aplica la configuración CORS a todas las rutas de la API
        registry.addMapping("/**") // Todos los endpoints
                //Orígenes permitidos: servidor de producción en RailWay y entorno local de desarrollo (puerto 3000)
                .allowedOrigins("https://proyecto-final-daw-production-5980.up.railway.app", "http://localhost:3000") // Origenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos. OPTIONS es necesario para peticiones preflight CORS
                .allowedHeaders("*") //permite todos los headers en las peticiones
                .allowCredentials(true); // Permitir credenciales (cookies, etc.). Necesario para la autenticación JWT
    }
}

