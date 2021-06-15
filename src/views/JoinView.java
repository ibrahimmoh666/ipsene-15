package views;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Speler;
import services.manageData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinView {
    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Join Room - IIPSENE Groep 15";
    public manageData cd = null;

    @FXML
    private Label stateMessage;

    @FXML
    private TextField tokenTextField;

    @FXML
    private TextField userNameTextField;

    public JoinView(Stage window, String token)  {
        this.window = window;
        showWindow();
        cd.token = token;
        this.tokenTextField.setText(token);
        try {
            manageData.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Join.fxml"));
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
    public void navigateToMain(ActionEvent event) {
        MainView mainView = new MainView(this.window);
    }

    @FXML
    public void tokenEntered(ActionEvent event) {
        final String tokenUserInput = tokenTextField.getText().replaceAll("\\s", "");
        tokenTextField.setText("");

        if (tokenUserInput.isEmpty()) {
            stateMessage.setText("* Please enter a token before pressing enter");
        } else if (!tokenUserInput.matches("\\d*")) {
            stateMessage.setText("* Invalid input - token must be a number");
        } else {
            stateMessage.setText("");
        }
    }

    @FXML
    public void joinRoom(ActionEvent event) throws Exception {
        if (cd == null) {
            this.cd = manageData.getInstance();
        }
        cd.addUser(userNameTextField.getText(), tokenTextField.getText());
        cd.token = tokenTextField.getText();
        System.out.println(userNameTextField.getText());
        LobbyView lobbyView = new LobbyView(this.window);
    }

}
