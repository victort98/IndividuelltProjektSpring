package com.example.demo.entities;


public class Ability {

    private GeneralInfo ability;

    public Ability() {
    }

    public Ability(String name, String url) {
        this.ability = new GeneralInfo(name, url);
    }

    public GeneralInfo getAbility() {
        return ability;
    }

    public void setAbility(GeneralInfo ability) {
        this.ability = ability;
    }
}
