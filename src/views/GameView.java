package views;

import controllers.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameView {
    private SceneController sceneController = new SceneController();

    @FXML
    private AnchorPane gamePopup;

    @FXML
    private AnchorPane gamePane;

    @FXML
    public void goToMainMenu(ActionEvent event) {
        sceneController.switchScene(event, "MainScene");
    }

    @FXML
    public void showPopup(ActionEvent event) {
//        gamePopup.setVisible(true);
        Popup p = new Popup(this.gamePane, "Your turn!", "It's your turn to roll the set of dice.");
        p.show();
    }

    @FXML
    public void closePopup(ActionEvent event) {
        gamePopup.setVisible(false);
    }
}
