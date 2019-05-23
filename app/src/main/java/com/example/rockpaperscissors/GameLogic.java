package com.example.rockpaperscissors;

import java.util.Random;

public class GameLogic {

    private int pointLimit;

    GameLogic(int pointLimit) {
        this.pointLimit = pointLimit;
    }

    private enum Choice {
        ROCK,
        PAPER,
        SCISSORS;


        public Choice getRandomChoice() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public int getPointLimit() {
        return pointLimit;
    }

    public ()



}
