package application;

import javafx.application.Application;
import javafx.stage.Stage;
import views.MainView;

public class Main extends Application {
    @Override
    public void start(Stage window) throws Exception {
        MainView mainView = new MainView(window);
    }

    public static void main(String[] args) {
        launch(args);
    }
}