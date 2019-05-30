package com.example.rockpaperscissors;

import java.util.Random;

public enum PlayerChoice {
    ROCK,
    PAPER,
    SCISSORS,
    NONE;

    public static PlayerChoice getRandomChoice() {
        Random random = new Random();
        // A random choice is picked from ROCK, PAPER, SCISSORS only!
        // NONE should NOT be picked as a random bot choice!
        return values()[random.nextInt(values().length-1)];
    }
}