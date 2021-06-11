package views;

import controllers.SceneController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Speler;
import services.firebaseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GameView extends Application {
    private SceneController sceneController = new SceneController();

    @FXML
    private Label playerName;
    private Label playerMoney;

    @FXML
    public void goToMainMenu(ActionEvent event) {
        sceneController.switchScene(event, "MainScene");
    }

    public GameView() {
        addUsertoBoard();
    }

    @Override
    public void start(Stage stage) throws Exception {
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

    }

    public static void main(String[] args) {

    }

}
