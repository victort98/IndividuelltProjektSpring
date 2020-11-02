package com.example.demo.entities;

import java.util.List;

public class ListOfPokemon {
    private List<GeneralInfo> results;

    public ListOfPokemon() {
    }

    public ListOfPokemon(List<GeneralInfo> results) {
        this.results = results;
    }

    public List<GeneralInfo> getResults() {
        return results;
    }

    public void setResults(List<GeneralInfo> results) {
        this.results = results;
    }
}
