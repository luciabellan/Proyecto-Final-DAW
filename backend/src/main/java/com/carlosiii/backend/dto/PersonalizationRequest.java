package com.carlosiii.backend.dto;

import java.util.List;

// DTO para manejar las solicitudes de personalización de historias
public class PersonalizationRequest {
    // Número total de niños para los que se personaliza la historia
    private int numberOfChildren;
    // Lista de personalizaciones individuales para cada niño
    private List<ChildCustomization> children;

    // Getters y setters para acceder a los datos
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public List<ChildCustomization> getChildren() {
        return children;
    }

    public void setChildren(List<ChildCustomization> children) {
        this.children = children;
    }
}
// Clase interna que define la personalización para cada niño
class ChildCustomization {

    private String name;
    private String gender;
    private String selectedCharacter;

    // Getters y setters para los atributos de personalización

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}
