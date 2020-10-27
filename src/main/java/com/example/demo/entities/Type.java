package com.example.demo.entities;

public class Type {
    private GeneralInfo type;

    public Type() {
    }

    public Type(String name, String url) {
        this.type = new GeneralInfo(name, url);
    }

    public GeneralInfo getType() {
        return type;
    }

    public void setType(GeneralInfo type) {
        this.type = type;
    }
}
