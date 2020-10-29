package com.example.demo.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Pokemon {

    @Id
    private String id;
    private String name;
    private String height;
    private String weight;
    private String baseExperience;
    private String locationEncounter;
    private List<Type> types;
    private List<Ability> abilities;
    private List<Game> games;
    private Specie specie;

    public Pokemon() {
    }

    public Pokemon(String name, String height, String weight, List<Type> types) {
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

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
