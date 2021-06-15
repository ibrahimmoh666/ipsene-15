package views;

import com.sun.javafx.scene.control.InputField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinView {
    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Join Room - IIPSENE Groep 15";
    public String token;

    @FXML
    private Label stateMessage;

    @FXML
    private TextField tokenTextField;

    @FXML
    private TextField userNameTextField;

    public JoinView(Stage window, String token) {
        this.window = window;
        showWindow();
        this.token = token;
        this.tokenTextField.setText(token);
    }

    public String getToken() {
        return token;
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Join2.fxml"));
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
    public void joinRoom(ActionEvent event) {
        //Todo: Save user information to firebase
        LobbyView lobbyView = new LobbyView(this.window);

    }

}
