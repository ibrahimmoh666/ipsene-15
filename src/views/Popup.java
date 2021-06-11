package views;

import controllers.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Popup {
    private String title;
    private String message;

    @FXML
    private AnchorPane gamePane;

    private VBox popupBox = new VBox();

    public Popup(AnchorPane gamePane, String title, String message) {
        this.title = title;
        this.message = message;
        this.gamePane = gamePane;
        createPopupElements();
    }

    private void createPopupElements() {
        popupBox.setPrefSize(600, 400);
        popupBox.setPadding(new Insets(40));
        popupBox.setSpacing(20);
        popupBox.setAlignment(Pos.TOP_CENTER);
        popupBox.getStyleClass().add("popup-background");

        Label titleLabel = new Label(this.title);
        titleLabel.getStyleClass().add("popup-title");

        Label messageLabel = new Label(this.message);
        messageLabel.getStyleClass().add("popup-message");

        Button closePopupBtn = new Button("OK");
        closePopupBtn.getStyleClass().add("popup-btn");
        closePopupBtn.setOnAction(e -> {
            destroy();
        });

        popupBox.getChildren().addAll(titleLabel, messageLabel, closePopupBtn);
    }

    public void show() {
        gamePane.getChildren().add(popupBox);
    }

    public void destroy() {
        gamePane.getChildren().remove(popupBox);
    }
}
