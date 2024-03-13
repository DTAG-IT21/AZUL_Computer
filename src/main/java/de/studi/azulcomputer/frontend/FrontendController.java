package de.studi.azulcomputer.frontend;

import de.studi.azulcomputer.logic.TileBag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FrontendController {
    private TileBag tileBag = new TileBag(); // Instanzvariable tileBag erstellen und initialisieren

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
    private BarChart<String, Number> barChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label lbl_einzel;

    @FXML
    private Label lbl_paar;

    @FXML
    private Label lbl_dreier;

    @FXML
    private Label lbl_vierer;

    @FXML
    private Label lbl_blau;

    @FXML
    private Label lbl_gelb;

    @FXML
    private Label lbl_tuerkis;

    @FXML
    private Label lbl_rot;

    @FXML
    private Label lbl_schwarz;

    @FXML
    private Label lbl_einzel_blau;

    @FXML
    private Label lbl_einzel_gelb;

    @FXML
    private Label lbl_einzel_tuerkis;

    @FXML
    private Label lbl_einzel_rot;

    @FXML
    private Label lbl_einzel_schwarz;

    @FXML
    private Label lbl_paar_blau;

    @FXML
    private Label lbl_paar_gelb;

    @FXML
    private Label lbl_paar_tuerkis;

    @FXML
    private Label lbl_paar_rot;

    @FXML
    private Label lbl_paar_schwarz;

    @FXML
    private Label lbl_dreier_blau;

    @FXML
    private Label lbl_dreier_gelb;

    @FXML
    private Label lbl_dreier_tuerkis;

    @FXML
    private Label lbl_dreier_rot;

    @FXML
    private Label lbl_dreier_schwarz;

    @FXML
    private Label lbl_vierer_blau;

    @FXML
    private Label lbl_vierer_gelb;

    @FXML
    private Label lbl_vierer_tuerkis;

    @FXML
    private Label lbl_vierer_rot;

    @FXML
    private Label lbl_vierer_schwarz;

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


        lbl_einzel_blau.setText("0.0%");
        lbl_einzel_gelb.setText("0.0%");
        lbl_einzel_tuerkis.setText("0.0%");
        lbl_einzel_rot.setText("0.0%");
        lbl_einzel_schwarz.setText("0.0%");
        lbl_paar_blau.setText("0.0%");
        lbl_paar_gelb.setText("0.0%");
        lbl_paar_tuerkis.setText("0.0%");
        lbl_paar_rot.setText("0.0%");
        lbl_paar_schwarz.setText("0.0%");
        lbl_dreier_blau.setText("0.0%");
        lbl_dreier_gelb.setText("0.0%");
        lbl_dreier_tuerkis.setText("0.0%");
        lbl_dreier_rot.setText("0.0%");
        lbl_dreier_schwarz.setText("0.0%");
        lbl_vierer_blau.setText("0.0%");
        lbl_vierer_gelb.setText("0.0%");
        lbl_vierer_tuerkis.setText("0.0%");
        lbl_vierer_rot.setText("0.0%");
        lbl_vierer_schwarz.setText("0.0%");


        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Farben");
        yAxis.setLabel("Wahrscheinlichkeit");


        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Blau", 0.2));
        series.getData().add(new XYChart.Data<>("Gelb", 0.2));
        series.getData().add(new XYChart.Data<>("Rot", 0.2));
        series.getData().add(new XYChart.Data<>("Türkis", 0.2));
        series.getData().add(new XYChart.Data<>("Schwarz", 0.2));

        barChart.getData().add(series);


      initializePieChart();

      initializeBarChart();

        // Standardwert für jede ChoiceBox auf 0 setzen
        blueChoiceBox.setValue(0);
        yellowChoiceBox.setValue(0);
        turquoiseChoiceBox.setValue(0);
        redChoiceBox.setValue(0);
        blackChoiceBox.setValue(0);




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


        //Update das Kuchendiagramm
        updatePieChart(tileBag, pieChart);

        //Update des BarCharts
        updateBarChart(tileBag, barChart);
    }



    @FXML
    private void reset() {
        tileBag.reset(); // Tile-Sack neu initialisieren



        // Standardwert für jede ChoiceBox auf 0 setzen
        blueChoiceBox.setValue(0);
        yellowChoiceBox.setValue(0);
        turquoiseChoiceBox.setValue(0);
        redChoiceBox.setValue(0);
        blackChoiceBox.setValue(0);


     initializePieChart();

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

    public void initializePieChart(){
            // Erstellen von Datenpunkten für das Diagramm mit Zahlenwerten
            List<PieChart.Data> pieChartData = new ArrayList<>();
    pieChartData.add(new PieChart.Data("Blau: " + tileBag.getTileCount("Blau"), tileBag.getTileCount("Blau")));
    pieChartData.add(new PieChart.Data("Gelb: " + tileBag.getTileCount("Gelb"), tileBag.getTileCount("Gelb")));
    pieChartData.add(new PieChart.Data("Türkis: " + tileBag.getTileCount("Türkis"), tileBag.getTileCount("Türkis")));
    pieChartData.add(new PieChart.Data("Rot: " + tileBag.getTileCount("Rot"), tileBag.getTileCount("Rot")));
    pieChartData.add(new PieChart.Data("Schwarz: " + tileBag.getTileCount("Schwarz"), tileBag.getTileCount("Schwarz")));

    // Hinzufügen der Datenpunkte zum Diagramm
        pieChart.getData().clear();
        pieChart.getData().addAll(pieChartData);

    // Anpassen der Farben für jeden Datenpunkt
    for (PieChart.Data data : pieChartData) {
        Node node = data.getNode();
        if (node != null) {
            switch (data.getName().split(": ")[0]) {
                case "Blau":
                    node.setStyle("-fx-pie-color: blue;");
                    break;
                case "Gelb":
                    node.setStyle("-fx-pie-color: yellow;");
                    break;
                case "Rot":
                    node.setStyle("-fx-pie-color: red;");
                    break;
                case "Türkis":
                    node.setStyle("-fx-pie-color: turquoise;");
                    break;
                case "Schwarz":
                    node.setStyle("-fx-pie-color: black;");
                    break;
                default:
                    // Fallback-Farbe
                    node.setStyle("-fx-pie-color: gray;");
                    break;
            }
        }
    }
}

    public void updatePieChart(TileBag tileBag, PieChart pieChart) {
        List<PieChart.Data> pieChartData = new ArrayList<>();
        pieChartData.add(new PieChart.Data("Blau: " + tileBag.getTileCount("Blau"), tileBag.getTileCount("Blau")));
        pieChartData.add(new PieChart.Data("Gelb: " + tileBag.getTileCount("Gelb"), tileBag.getTileCount("Gelb")));
        pieChartData.add(new PieChart.Data("Türkis: " + tileBag.getTileCount("Türkis"), tileBag.getTileCount("Türkis")));
        pieChartData.add(new PieChart.Data("Rot: " + tileBag.getTileCount("Rot"), tileBag.getTileCount("Rot")));
        pieChartData.add(new PieChart.Data("Schwarz: " + tileBag.getTileCount("Schwarz"), tileBag.getTileCount("Schwarz")));

        pieChart.getData().clear();
        pieChart.getData().addAll(pieChartData);

        for (PieChart.Data data : pieChartData) {
            Node node = data.getNode();
            if (node != null) {
                switch (data.getName().split(": ")[0]) {
                    case "Blau":
                        node.setStyle("-fx-pie-color: blue;");
                        break;
                    case "Gelb":
                        node.setStyle("-fx-pie-color: yellow;");
                        break;
                    case "Rot":
                        node.setStyle("-fx-pie-color: red;");
                        break;
                    case "Türkis":
                        node.setStyle("-fx-pie-color: turquoise;");
                        break;
                    case "Schwarz":
                        node.setStyle("-fx-pie-color: black;");
                        break;
                    default:
                        // Fallback-Farbe
                        node.setStyle("-fx-pie-color: gray;");
                        break;
                }
            }
        }
    }

    public void initializeBarChart() {
        // Anzahl der Gesamtsteine im Beutel
        int totalTiles = tileBag.getTotalTileCount();

        // Berechnung der Wahrscheinlichkeit für jede Farbe
        double blueProbability = tileBag.calculateProbability("Blau");
        double yellowProbability = tileBag.calculateProbability("Gelb");
        double turquoiseProbability = tileBag.calculateProbability("Türkis");
        double redProbability = tileBag.calculateProbability("Rot");
        double blackProbability = tileBag.calculateProbability("Schwarz");

        // Aktualisierung des Barcharts
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Blau", blueProbability));
        series.getData().add(new XYChart.Data<>("Gelb", yellowProbability));
        series.getData().add(new XYChart.Data<>("Türkis", turquoiseProbability));
        series.getData().add(new XYChart.Data<>("Rot", redProbability));
        series.getData().add(new XYChart.Data<>("Schwarz", blackProbability));

        // Farbgebung für jeden Balken
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            if (node != null) {
                switch (data.getXValue()) {
                    case "Blau":
                        node.setStyle("-fx-bar-fill: blue;");
                        break;
                    case "Gelb":
                        node.setStyle("-fx-bar-fill: yellow;");
                        break;
                    case "Türkis":
                        node.setStyle("-fx-bar-fill: turquoise;");
                        break;
                    case "Rot":
                        node.setStyle("-fx-bar-fill: red;");
                        break;
                    case "Schwarz":
                        node.setStyle("-fx-bar-fill: black;");
                        break;
                    default:
                        // Fallback-Farbe
                        node.setStyle("-fx-pie-color: gray;");
                        break;
                }
            }
        }


        barChart.getData().clear(); // Vorherige Daten löschen
        barChart.getData().add(series);
    }

    public void updateBarChart(TileBag tileBag, BarChart<String, Number> barChart) {
        // Anzahl der Gesamtsteine im Beutel
        int totalTiles = tileBag.getTotalTileCount();

        // Berechnung der Wahrscheinlichkeit für jede Farbe
        double blueProbability = tileBag.calculateProbability("Blau");
        double yellowProbability = tileBag.calculateProbability("Gelb");
        double turquoiseProbability = tileBag.calculateProbability("Türkis");
        double redProbability = tileBag.calculateProbability("Rot");
        double blackProbability = tileBag.calculateProbability("Schwarz");

        // Aktualisierung des Barcharts
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Blau", blueProbability));
        series.getData().add(new XYChart.Data<>("Gelb", yellowProbability));
        series.getData().add(new XYChart.Data<>("Türkis", turquoiseProbability));
        series.getData().add(new XYChart.Data<>("Rot", redProbability));
        series.getData().add(new XYChart.Data<>("Schwarz", blackProbability));

        // Farbgebung für jeden Balken
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            if (node != null) {
                switch (data.getXValue()) {
                    case "Blau":
                        node.setStyle("-fx-bar-fill: blue;");
                        break;
                    case "Gelb":
                        node.setStyle("-fx-bar-fill: yellow;");
                        break;
                    case "Türkis":
                        node.setStyle("-fx-bar-fill: turquoise;");
                        break;
                    case "Rot":
                        node.setStyle("-fx-bar-fill: red;");
                        break;
                    case "Schwarz":
                        node.setStyle("-fx-bar-fill: black;");
                        break;
                    default:
                        // Fallback-Farbe
                        node.setStyle("-fx-pie-color: gray;");
                        break;
                }
            }
        }

        barChart.getData().clear(); // Vorherige Daten löschen
        barChart.getData().add(series);
    }

}
