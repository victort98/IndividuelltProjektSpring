package com.example.demo.entities;

public class Specie {
    private GeneralInfo specie;

    public Specie() {
    }

    public Specie(String name, String url) {
        this.specie = new GeneralInfo(name, url);
    }

    public GeneralInfo getSpecie() {
        return specie;
    }

    public void setSpecie(GeneralInfo specie) {
        this.specie = specie;
    }
}
