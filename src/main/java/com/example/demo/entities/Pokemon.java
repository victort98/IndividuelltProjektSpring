package com.example.demo.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Pokemon {

    @Id
    private String id;
    private String name;
    private String height;
    private String weight;
    private List<String> types;
    private String baseExperience;

    public Pokemon() {
    }

    public Pokemon(String name, String height, String weight, List<String> types) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.types = types;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
