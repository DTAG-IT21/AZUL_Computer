package de.studi.azulcomputer.frontend;

import de.studi.azulcomputer.logic.TileBag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class FrontendController {
    private TileBag tileBag = new TileBag(); // Instanzvariable tileBag erstellen und initialisieren

    @FXML
    private Label probabilityBlueLabel;

    @FXML
    private Label probabilityYellowLabel;

    @FXML
    private Label probabilityTurquoiseLabel;

    @FXML
    private Label probabilityRedLabel;

    @FXML
    private Label probabilityBlackLabel;

    @FXML
    private Label blueLabel;

    @FXML
    private Label yellowLabel;

    @FXML
    private Label turquoiseLabel;

    @FXML
    private Label redLabel;

    @FXML
    private Label blackLabel;


    @FXML
    private ChoiceBox<Integer> blueChoiceBox;

    @FXML
    private ChoiceBox<Integer> yellowChoiceBox;

    @FXML
    private ChoiceBox<Integer> turquoiseChoiceBox;

    @FXML
    private ChoiceBox<Integer> redChoiceBox;

    @FXML
    private ChoiceBox<Integer> blackChoiceBox;

    @FXML
    private Label messageLabel;

    @FXML
   public void initialize() {
        // Auswahloptionen von 0 bis 25 für jede ChoiceBox setzen
        for (int i = 0; i <= 25; i++) {
            blueChoiceBox.getItems().add(i);
            yellowChoiceBox.getItems().add(i);
            turquoiseChoiceBox.getItems().add(i);
            redChoiceBox.getItems().add(i);
            blackChoiceBox.getItems().add(i);
        }

        // Standardwert für jede ChoiceBox auf 0 setzen
        blueChoiceBox.setValue(0);
        yellowChoiceBox.setValue(0);
        turquoiseChoiceBox.setValue(0);
        redChoiceBox.setValue(0);
        blackChoiceBox.setValue(0);

        // Standardwert für jedes Label auf 25 setzen
        blueLabel.setText(String.valueOf(tileBag.getTileCount("Blau")));
        yellowLabel.setText(String.valueOf(tileBag.getTileCount("Gelb")));
        turquoiseLabel.setText(String.valueOf(tileBag.getTileCount("Türkis")));
        redLabel.setText(String.valueOf(tileBag.getTileCount("Rot")));
        blackLabel.setText(String.valueOf(tileBag.getTileCount("Schwarz")));


    }

    @FXML
    public void ziehen() {
        int blueCount = blueChoiceBox.getValue();
        int yellowCount = yellowChoiceBox.getValue();
        int turquoiseCount = turquoiseChoiceBox.getValue();
        int redCount = redChoiceBox.getValue();
        int blackCount = blackChoiceBox.getValue();

        int sum = blueChoiceBox.getValue() + yellowChoiceBox.getValue() + turquoiseChoiceBox.getValue()
                + redChoiceBox.getValue() + blackChoiceBox.getValue();

        if (sum != 20) {
            showDialog();
            return; // Beende die Methode, wenn die Summe nicht 20 ergibt
        }

        // Entferne die entsprechenden Steine aus dem Sack
        tileBag.removeTiles("Blau", blueCount);
        tileBag.removeTiles("Gelb", yellowCount);
        tileBag.removeTiles("Türkis", turquoiseCount);
        tileBag.removeTiles("Rot", redCount);
        tileBag.removeTiles("Schwarz", blackCount);

        //Don´t Repeat Yourself potential!
        // Setze die aktuellen Mengen der Farben auf den Labels
        blueLabel.setText(String.valueOf(tileBag.getTileCount("Blau")));
        yellowLabel.setText(String.valueOf(tileBag.getTileCount("Gelb")));
        turquoiseLabel.setText(String.valueOf(tileBag.getTileCount("Türkis")));
        redLabel.setText(String.valueOf(tileBag.getTileCount("Rot")));
        blackLabel.setText(String.valueOf(tileBag.getTileCount("Schwarz")));

        // Berechne und setze die Wahrscheinlichkeiten
        calculateAndSetProbabilities();
    }

    private void calculateAndSetProbabilities() {


        probabilityBlueLabel.setText(String.format("%.2f", tileBag.calculateProbability("Blau") * 100) + "%");
        probabilityYellowLabel.setText(String.format("%.2f", tileBag.calculateProbability("Gelb") * 100) + "%");
        probabilityTurquoiseLabel.setText(String.format("%.2f", tileBag.calculateProbability("Türkis") * 100) + "%");
        probabilityRedLabel.setText(String.format("%.2f", tileBag.calculateProbability("Rot") * 100) + "%");
        probabilityBlackLabel.setText(String.format("%.2f", tileBag.calculateProbability("Schwarz") * 100) + "%");

    }

          @FXML
        private void reset() {
            tileBag.reset(); // Tile-Sack neu initialisieren

            // Labels mit neuen Anfangswerten aktualisieren
            blueLabel.setText(String.valueOf(tileBag.getTileCount("Blau")));
            yellowLabel.setText(String.valueOf(tileBag.getTileCount("Gelb")));
            turquoiseLabel.setText(String.valueOf(tileBag.getTileCount("Türkis")));
            redLabel.setText(String.valueOf(tileBag.getTileCount("Rot")));
            blackLabel.setText(String.valueOf(tileBag.getTileCount("Schwarz")));

            // Wahrscheinlichkeiten auf 20% setzen
            probabilityBlueLabel.setText("20%");
            probabilityYellowLabel.setText("20%");
            probabilityTurquoiseLabel.setText("20%");
            probabilityRedLabel.setText("20%");
            probabilityBlackLabel.setText("20%");


              // Standardwert für jede ChoiceBox auf 0 setzen
              blueChoiceBox.setValue(0);
              yellowChoiceBox.setValue(0);
              turquoiseChoiceBox.setValue(0);
              redChoiceBox.setValue(0);
              blackChoiceBox.setValue(0);

          }

    @FXML
    void showDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Ungültige Auswahl");
        alert.setContentText("Die Summe der Steine muss 20 ergeben!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                System.out.println("OK!");
            } else if (result.get() == ButtonType.CANCEL) {
                System.out.println("Never!");
            }
        } else {
            System.out.println("Alert geschlossen");
        }
    }



}






