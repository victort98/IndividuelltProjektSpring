package com.example.demo.entities;

public class Type {
    private NameAndUrl type;

    public Type() {
    }

    public Type(String name, String url) {
        this.type = new NameAndUrl(name, url);
    }

    public NameAndUrl getType() {
        return type;
    }

    public void setType(NameAndUrl type) {
        this.type = type;
    }
}
