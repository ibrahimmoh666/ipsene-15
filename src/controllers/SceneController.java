package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    @FXML
    public void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../scenes/" + fxmlFile + ".fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException IOEx) {
            IOEx.printStackTrace();
        }
    }

    @FXML
    public void showMainMenu(ActionEvent event) {
        switchScene(event, "MainScene");
    }

    @FXML
    public void showJoinMenu(ActionEvent event) {
        switchScene(event, "JoinScene");
    }

    @FXML
    public void showCreateMenu(ActionEvent event) {
        switchScene(event, "CreateScene");
    }

    @FXML
    public void showGameMenu(ActionEvent event) {
        switchScene(event, "GameMenu");
    }
}
