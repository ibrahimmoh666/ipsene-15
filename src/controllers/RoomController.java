package controllers;

import models.TokenGenerator;
import services.manageData;

import java.io.IOException;

public class RoomController {
    private TokenGenerator tokenGenerator = new TokenGenerator();
    public manageData cd;

    {
        try {
            cd = manageData.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RoomController() {
    }

    public void addUserToLobby(){

    }

    public void updatePlayers(){
        cd.Listen("spel", getToken());
    }

    public String getToken() {
        return tokenGenerator.getRandomToken();
    }
}
