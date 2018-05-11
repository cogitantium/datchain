package dk.aau.cs.a311c.datchain.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Wrapper extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Datchain");
        MainScreen.screen(primaryStage);
    }

}
