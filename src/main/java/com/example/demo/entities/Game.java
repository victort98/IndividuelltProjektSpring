package com.example.demo.entities;

public class Game {
    private GeneralInfo version;
    private int game_index;

    public Game() {
    }

    public Game(int id, String name, String url) {
        this.version = new GeneralInfo(name, url);
        this.game_index = id;
    }

    public GeneralInfo getVersion() {
        return version;
    }

    public void setVersion(GeneralInfo version) {
        this.version = version;
    }

    public int getGame_index() {
        return game_index;
    }

    public void setGame_index(int game_index) {
        this.game_index = game_index;
    }
}
