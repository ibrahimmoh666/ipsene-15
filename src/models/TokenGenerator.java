package models;

import java.util.Random;

public class TokenGenerator {
    private final int tokenLength = 5;
    private Random rnd = new Random();

    private String createRandomToken() {
        StringBuilder tokenStr = new StringBuilder();

        for (int i = 0; i < this.tokenLength; i++) {
            // We don't want the token to start with a zero, after the first index it doesn't matter
            tokenStr.append((i == 0) ? rnd.nextInt((9 - 1) + 1) + 1 : rnd.nextInt(10));
        }

        return tokenStr.toString();
    }

    public String getRandomToken() {
        return createRandomToken();
    }
}
