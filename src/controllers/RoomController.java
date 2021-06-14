package controllers;

import models.TokenGenerator;

public class RoomController {
    private TokenGenerator tokenGenerator = new TokenGenerator();

    public String getToken() {
        return tokenGenerator.getRandomToken();
    }
}
