package com.example.rockpaperscissors;

public class Player {

    private String name;
    private int score;
    private boolean isHuman;
    private

    Player(String name, boolean isHuman) {
        this.name = name;
        this.score = 0;
        this.isHuman = isHuman;
    }

    public String getPlayerName() {
        return name;
    }

    public void incrementScore() {
        this.score++;
    }

    public boolean isHuman() {
        return this.isHuman;
    }


}
