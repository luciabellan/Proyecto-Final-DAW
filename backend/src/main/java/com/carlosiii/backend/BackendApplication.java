package com.carlosiii.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Clase principal que inicia la aplicación Spring Boot
@SpringBootApplication // Anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan
public class BackendApplication {

	public static void main(String[] args) {// Método main que inicia la aplicación
		// Inicia el contenedor de Spring Boot y la aplicación
		SpringApplication.run(BackendApplication.class, args);
	}
}
