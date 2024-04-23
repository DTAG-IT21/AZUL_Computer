package de.studi.azulcomputer.plugin.database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/de/studi/azulcomputer/frontend/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 1024);
        stage.setTitle("Azul Computer");
        stage.setScene(scene);
        stage.show();
    }
}