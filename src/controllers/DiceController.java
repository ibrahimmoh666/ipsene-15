package controllers;

import java.util.Random;

public class DiceController {
    private int maxThrow = 6;

    public int[] rollDices() {
        Random rnd = new Random();
        return new int[] {rnd.nextInt(maxThrow) + 1, rnd.nextInt(maxThrow) + 1};
    }
}
