package views;

import controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Speler;
import services.firebaseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameView {
    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Game - IIPSENE Groep 15";

    public GameView(Stage window) {
        this.window = window;
        showWindow();
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Game.fxml"));
            loader.setController(this);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root, this.windowWidth, this.windowHeight);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            window.setScene(scene);
            window.setResizable(false);
            window.setTitle(this.windowTitle);
            window.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void addUsertoBoard()  {
        firebaseService fs = null;
        ArrayList<Speler> spelers = new ArrayList<Speler>();
        try {
            fs = new firebaseService();
            fs.addUser("Alavi", "roomToken");
            fs.addUser("Plinio", "roomToken");
            fs.addUser("Ibrahim", "roomToken");
            fs.addUser("Sowi", "roomToken");
            spelers = fs.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> players = new HashMap<String, String>();
        for (Speler speler: spelers){
            players.put(speler.getName(), Integer.toString(speler.getMoney()));
        }

        setText(players);
    }

    @FXML
    private void setText(HashMap<String, String> players){

        int i = 0;
        for (Map.Entry<String, String> entry : players.entrySet()) {
            System.out.println(entry.getKey() +" - "+ entry.getValue());
            this.playerName.setText(entry.getValue());
            this.playerMoney.setText(entry.getKey());
        }

    public void navigateToMain(ActionEvent event) {
        MainView mainView = new MainView(this.window);
    }
}
