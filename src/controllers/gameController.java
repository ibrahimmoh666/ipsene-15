package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Speler;
import services.firebaseService;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class gameController {

    public String token;

    public gameController(String token) {
        this.token = token;
    }

    public void start(Stage stage) throws Exception {
        System.out.println("Start gameController");
    }



    public static void main(String[] args) {
    }

}
