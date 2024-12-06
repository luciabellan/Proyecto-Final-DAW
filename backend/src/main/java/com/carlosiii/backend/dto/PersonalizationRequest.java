package com.carlosiii.backend.dto;

import java.util.List;

public class PersonalizationRequest {
    
    private int numberOfChildren;
    private List<ChildCustomization> children;

    // Getters y setters...

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

class ChildCustomization {

    private String name;
    private String gender;
    private String selectedCharacter;

    // Getters y setters...

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
