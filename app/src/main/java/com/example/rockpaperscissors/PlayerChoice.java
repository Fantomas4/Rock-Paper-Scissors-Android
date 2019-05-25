package com.example.rockpaperscissors;

import java.util.Random;

public enum PlayerChoice {
    ROCK,
    PAPER,
    SCISSORS;

    public static PlayerChoice getRandomChoice() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}