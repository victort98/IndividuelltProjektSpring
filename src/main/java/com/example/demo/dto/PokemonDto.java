package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonDto {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Height")
    private String height;
    @JsonProperty("Weight")
    private String weight;
}
