package views;

import controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GameView {
    private SceneController sceneController = new SceneController();

    @FXML
    public void goToMainMenu(ActionEvent event) {
        sceneController.switchScene(event, "MainScene");
    }
}
