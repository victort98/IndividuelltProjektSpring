package com.example.demo.dto;

import com.example.demo.entities.Ability;
import com.example.demo.entities.Game;
import com.example.demo.entities.Specie;
import com.example.demo.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonDto {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Height")
    private String height;
    @JsonProperty("Weight")
    private String weight;
    @JsonProperty("Base Experience")
    private String baseExperience;
    @JsonProperty("Location Encounter")
    private String locationEncounter;
    @JsonProperty("Types")
    private List<Type> types;
    @JsonProperty("Abilities")
    private List<Ability> abilities;
    @JsonProperty("Game implemented")
    private List<Game> games;
    @JsonProperty("Species")
    private Specie specie;

    public PokemonDto(String name, String height, String weight, String baseExperience, String locationEncounter,
                      List<Type> types, List<Ability> abilities, List<Game> games, Specie specie) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.locationEncounter = locationEncounter;
        this.types = types;
        this.abilities = abilities;
        this.games = games;
        this.specie = specie;
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

    public String getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(String baseExperience) {
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }
}
