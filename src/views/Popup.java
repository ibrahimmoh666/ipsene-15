package views;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Popup {
    private String title;
    private String message;

    @FXML
    private AnchorPane currentPane;

    @FXML
    private Label popupTitle;

    @FXML
    private Label popupMessage;

    @FXML
    private Button popupButton;

    private VBox popupBox = new VBox();

    public Popup(AnchorPane gamePane, String title, String message) {
        this.title = title;
        this.message = message;
        this.currentPane = gamePane;
        createPopupElements();
    }

    private void createPopupElements() {
        popupBox.setPrefSize(600, 400);
        popupBox.setPadding(new Insets(40));
        popupBox.setSpacing(20);
        popupBox.setAlignment(Pos.CENTER);
        popupBox.setLayoutX(400);
        popupBox.setLayoutY(200);
        popupBox.setVisible(false);
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
        currentPane.getChildren().add(popupBox);
    }

    public void show() {
        popupBox.setVisible(true);
    }

    public void hide() {
        popupBox.setVisible(false);
    }

    @FXML
    public void destroy() {
        currentPane.getChildren().remove(popupBox);
    }
}
