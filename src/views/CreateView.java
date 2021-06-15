package views;

import controllers.RoomController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.manageData;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;

public class CreateView {
    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Create Room - IIPSENE Groep 15";
    public String token;
    private final manageData cd = manageData.getInstance();

    private RoomController roomController = new RoomController();

    @FXML
    private TextField tokenTextField;

    public CreateView(Stage window) throws IOException {
        this.window = window;
        showWindow();
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Create.fxml"));
            loader.setController(this);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root, this.windowWidth, this.windowHeight);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            window.setScene(scene);
            window.setResizable(false);
            window.setTitle(this.windowTitle);
            this.token = roomController.getToken();

            this.tokenTextField.setText(this.token);
            window.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void navigateToMain() {
        MainView mainView = new MainView(this.window);
    }

    @FXML
    public void navigateToJoin() throws Exception {
        cd.createRoom(this.token);

        System.out.println("Token sent to joinview:  "+ this.token);
        JoinView joinView = new JoinView(this.window, this.token);
    }

    @FXML
    public void copyTokenToClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(tokenTextField.getText());
        clipboard.setContents(transferable, null);
    }
}
