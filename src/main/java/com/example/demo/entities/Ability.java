package com.example.demo.entities;


public class Ability {

    private NameAndUrl ability;

    public Ability() {
    }

    public Ability(String name, String url) {
        this.ability = new NameAndUrl(name, url);
    }

    public NameAndUrl getAbility() {
        return ability;
    }

    public void setAbility(NameAndUrl ability) {
        this.ability = ability;
    }
}
