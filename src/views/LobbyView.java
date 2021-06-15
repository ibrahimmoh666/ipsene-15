package views;

import controllers.RoomController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.manageData;

import java.io.IOException;

public class LobbyView {

    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Lobby - IIPSENE Groep 15";
    private RoomController roomController = new RoomController();
    public manageData cd = null;

    @FXML
    private Label stateMessage;

    @FXML
    private Label tokenText;

    public LobbyView(Stage window) {
        this.window = window;
        showWindow();
        roomController.updatePlayers();
        try {
            manageData.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Lobby.fxml"));
            loader.setController(this);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root, this.windowWidth, this.windowHeight);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            window.setScene(scene);
            window.setResizable(false);
            window.setTitle(this.windowTitle);
            window.show();
            tokenText.setText(cd.token);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addUserstoLobby(){

    }

    @FXML
    public void navigateToMain(ActionEvent event) {
        MainView mainView = new MainView(this.window);
    }

    @FXML
    public void readyUp(ActionEvent event) {
        GameView gameView = new GameView(this.window);
    }

}
