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
    private Stage window;
    private Scene scene;
    private Parent root;

    public void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../scenes/" + fxmlFile + ".fxml"));
            window = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../scenes/application.css").toExternalForm());
            window.setScene(scene);
            window.setResizable(false);
            window.show();
        } catch (IOException IOEx) {
            IOEx.printStackTrace();
        }
    }
}
