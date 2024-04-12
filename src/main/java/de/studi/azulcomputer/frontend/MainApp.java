package de.studi.azulcomputer.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static de.studi.azulcomputer.backend.HypergeometricDistribution.hypergeometricDistribution;

public class MainApp extends Application {
    public static void main(String[] args) {

        launch();

        int N = 100; // Größe der Gesamtpopulation
        int M = 20;  // Anzahl der erfolgreichen Elemente in der Population
        int n = 4;   // Größe der Stichprobe
        int x = 2;   // Anzahl der erfolgreichen Elemente in der Stichprobe

        double probability = hypergeometricDistribution(N, M, n, x);
        System.out.println("Die Wahrscheinlichkeit, genau " + x + " erfolgreiche Elemente in einer Stichprobe der Größe " + n + " zu erhalten, beträgt: " + probability);


    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Azul Computer");
        stage.setScene(scene);
        stage.show();
    }
}