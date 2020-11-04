package com.example.demo.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Pokemon {

    @Id
    private String id;
    private String name;
    private int height;
    private int weight;
    private int baseExperience;
    private String locationEncounter;
    private List<Type> types;
    private List<Ability> abilities;

    public Pokemon() {
    }

    public Pokemon(String name, int height, int weight, int baseExperience, String locationEncounter, List<Type> types, List<Ability> abilities) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.locationEncounter = locationEncounter;
        this.types = types;
        this.abilities = abilities;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public String getLocationEncounter() {
        return locationEncounter;
    }

    public void setLocationEncounter(String locationEncounter) {
        this.locationEncounter = locationEncounter;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}