package views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Popup {
    private AnchorPane pane;
    private VBox popupBox;

    @FXML
    private Label popupTitle;

    @FXML
    private Label popupMessage;

    @FXML
    private HBox popupImages;

    public Popup(AnchorPane pane) {
        this.pane = pane;
        createOverlay();
    }

    private void createOverlay() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/resources/fxml/Popup.fxml"));
            loader.setController(this);
            this.popupBox = loader.load();
            hide();
            this.pane.getChildren().add(popupBox);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isVisible() {
        return this.popupBox.isVisible();
    }

    public void show() {
        this.popupBox.setVisible(true);
    }

    @FXML
    public void hide() {
        this.popupBox.setVisible(false);
    }

    public void setTitle(String text) {
        this.popupTitle.setText(text);
    }

    public void setMessage(String text) {
        this.popupMessage.setText(text);
    }

    public void addImage(Image img) {
        ImageView iv = new ImageView(img);
        iv.setFitWidth(150);
        iv.setFitHeight(150);
        this.popupImages.getChildren().add(iv);
    }

    public void removeImages() {
        this.popupImages.getChildren().removeAll();
    }

    public void remove() {
        this.pane.getChildren().remove(this.popupBox);
    }
}
