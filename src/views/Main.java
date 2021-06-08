package views;

import controllers.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/MainScene.fxml"));
        primaryStage.setTitle("Monopoly - IIPSENE Groep 15");
        primaryStage.getIcons().add(new Image("files/logo.png"));
        primaryStage.setScene(new Scene(root, 1400, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
