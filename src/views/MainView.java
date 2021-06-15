package views;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainView {
    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Monopoly - IIPSENE Groep 15";
    private final Image windowIcon = new Image(getClass().getResource("/resources/images/logo.png").toExternalForm());

    public MainView(Stage window) {
        this.window = window;
        showWindow();
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Main.fxml"));
            loader.setController(this);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root, this.windowWidth, this.windowHeight);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            window.setScene(scene);
            window.setResizable(false);
            window.setTitle(this.windowTitle);
            window.getIcons().add(this.windowIcon);
            window.show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void navigateToCreate() {
        CreateView createView = new CreateView(this.window);
    }

    @FXML
    public void navigateToJoin() {
        JoinView joinView = new JoinView(this.window, "");
    }

    @FXML
    public void navigateToRules() {

    }

    @FXML
    public void navigateToTest() {
        GameView gameView = new GameView(this.window, "testPhaseUsername");
    }

    @FXML
    public void exitApplication() {
        Platform.exit();
    }
}
