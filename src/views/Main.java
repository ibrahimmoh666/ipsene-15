package views;

import controllers.SceneController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private SceneController sceneController = new SceneController();

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/MainScene.fxml"));
        Scene scene = new Scene(root, 1400, 800);
        scene.getStylesheets().add(getClass().getResource("../scenes/application.css").toExternalForm());
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("Monopoly - IIPSENE Groep 15");
        window.getIcons().add(new Image("main/resources/images/logo.png"));
        window.show();
    }

    @FXML
    public void goToCreateRoom(ActionEvent event) {
        System.out.println("Create scene has yet to be made");
        /*sceneController.switchScene(event, "CreateScene");*/
    }

    @FXML
    public void goToJoinRoom(ActionEvent event) {
        sceneController.switchScene(event, "JoinScene");
    }

    @FXML
    public void goToGameRules(ActionEvent event) {
        sceneController.switchScene(event, "RulesScene");
    }

    @FXML
    public void goToGame(ActionEvent event) {
        sceneController.switchScene(event, "GameScene");
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
