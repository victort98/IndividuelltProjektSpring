package com.example.demo.entities;

import java.util.List;

public class ListOfPokemon {
    private List<NameAndUrl> results;

    public ListOfPokemon() {
    }

    public ListOfPokemon(List<NameAndUrl> results) {
        this.results = results;
    }

    public List<NameAndUrl> getResults() {
        return results;
    }

    public void setResults(List<NameAndUrl> results) {
        this.results = results;
    }
}
