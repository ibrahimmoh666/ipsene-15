package controllers;

import java.util.Random;

public class DiceController {
    public int[] rollDices() {
        Random rnd = new Random();
        return new int[] {rnd.nextInt(6) + 1, rnd.nextInt(6) + 1};
    }
}
