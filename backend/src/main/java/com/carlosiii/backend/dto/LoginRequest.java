package com.carlosiii.backend.dto;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://proyecto-final-daw-production-5980.up.railway.app")
public class LoginRequest {
    private String email;
    private String password;

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
