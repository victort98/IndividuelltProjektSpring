package com.example.demo.dto;

import com.example.demo.entities.Ability;
import com.example.demo.entities.Move;
import com.example.demo.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonDto {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Height")
    private int height;
    @JsonProperty("Weight")
    private int weight;
    @JsonProperty("Base Experience")
    private int baseExperience;
    @JsonProperty("Location Encounter")
    private String locationEncounter;
    @JsonProperty("Types")
    private List<Type> types;
    @JsonProperty("Abilities")
    private List<Ability> abilities;
    @JsonProperty("Moves")
    private List<Move> moves;

    public PokemonDto(String name, int height, int weight, int base_experience, String location_area_encounters, List<Type> types, List<Ability> abilities, List<Move> moves) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = base_experience;
        this.locationEncounter = location_area_encounters;
        this.types = types;
        this.abilities = abilities;
        this.moves = moves;
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

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
