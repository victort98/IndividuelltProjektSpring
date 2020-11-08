package com.example.demo.entities;

public class Move {
    private NameAndUrl move;

    public Move() {
    }

    public Move(String name, String url) {
        this.move = new NameAndUrl(name, url);
    }

    public NameAndUrl getMove() {
        return move;
    }

    public void setMove(NameAndUrl move) {
        this.move = move;
    }
}
