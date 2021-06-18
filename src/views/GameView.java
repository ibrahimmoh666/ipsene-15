package views;

import controllers.DiceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.BoardPositions;
import models.Player;

public class GameView {
    private Stage window;

    private final int windowWidth = 1400;
    private final int windowHeight = 800;
    private final String windowTitle = "Game - IIPSENE Groep 15";

    @FXML
    private AnchorPane gamePane;

    @FXML
    private Circle playerOne;
    private Player plr = new Player("John");

    public GameView(Stage window) {
        this.window = window;
        showWindow();
    }

    private void showWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Game.fxml"));
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
    public void rollDice() {
        DiceController diceController = new DiceController();
        int[] rolledDice = diceController.rollDices();
        int total = 0;

        Popup p = new Popup(gamePane);
        p.setTitle("You rolled");

        for (int i = 0; i < rolledDice.length; i++) {
            Image diceImg = new Image(String.format("/resources/images/dice-%d.png", rolledDice[i]));
            p.addImage(diceImg);
            total += rolledDice[i];
        }

        plr.addStepsToPosition(total);
        moveTest();

        p.setMessage("You've rolled a total of " + total);
        p.show();
    }

    @FXML
    public void moveTest() {
        BoardPositions bp = new BoardPositions();
        int[] layout = bp.getLayoutFromBoxNumber(plr.getPosition(), 2);
        playerOne.setLayoutX(layout[0]);
        playerOne.setLayoutY(layout[1]);
    }

    @FXML
    public void showPopup() {
        Popup p = new Popup(gamePane);
        p.setTitle("Your turn!");
        p.setMessage("Click the dice icon on the board to roll the set of dice");
        p.addImage(new Image("/resources/images/roll_dice.png"));
        p.show();
    }
}
