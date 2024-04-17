package de.studi.azulcomputer.adapters;

import de.studi.azulcomputer.backend.HypergeometricDistribution;
import de.studi.azulcomputer.backend.Tile;
import de.studi.azulcomputer.backend.TileBag;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProbabilitiesTabController {

    private final TileBag tileBag = new TileBag(); // Instanzvariable tileBag erstellen und initialisieren


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
    private Label lbl_gesamtanzahl_fliesen;


    @FXML
    public void initialize() {


        initializeChoiceBoxes();

        initializePieChart();

        initializeBarChart();

        initializeLabels();

    }

    public void initializeChoiceBoxes() {
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


    }

    public void initializePieChart() {

        lbl_gesamtanzahl_fliesen.setText("Gesamtanzahl Fliesen: " + tileBag.getTotalTileCount());
        // Erstellen von Datenpunkten für das Diagramm mit Zahlenwerten
        List<PieChart.Data> pieChartData = new ArrayList<>();
        pieChartData.add(new PieChart.Data("Blau: " + tileBag.getTileCount(Tile.colors.get("blue")), tileBag.getTileCount(Tile.colors.get("blue"))));
        pieChartData.add(new PieChart.Data("Gelb: " + tileBag.getTileCount(Tile.colors.get("yellow")), tileBag.getTileCount(Tile.colors.get("yellow"))));
        pieChartData.add(new PieChart.Data("Türkis: " + tileBag.getTileCount(Tile.colors.get("green")), tileBag.getTileCount(Tile.colors.get("green"))));
        pieChartData.add(new PieChart.Data("Rot: " + tileBag.getTileCount(Tile.colors.get("red")), tileBag.getTileCount(Tile.colors.get("red"))));
        pieChartData.add(new PieChart.Data("Schwarz: " + tileBag.getTileCount(Tile.colors.get("black")), tileBag.getTileCount(Tile.colors.get("black"))));

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


    public void initializeBarChart() {

        // Anzahl der Gesamtsteine im Beutel
        int totalTiles = tileBag.getTotalTileCount();

        // Berechnung der Wahrscheinlichkeit für jede Farbe
        double blueProbability = tileBag.calculateProbability(Tile.colors.get("blue"));
        double yellowProbability = tileBag.calculateProbability(Tile.colors.get("yellow"));
        double turquoiseProbability = tileBag.calculateProbability(Tile.colors.get("red"));
        double redProbability = tileBag.calculateProbability(Tile.colors.get("black"));
        double blackProbability = tileBag.calculateProbability(Tile.colors.get("green"));

        //Beschriftung der Achsen
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Farben");
        yAxis.setLabel("Wahrscheinlichkeit");


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
                        node.setStyle("-fx-bar-fill: gray;");
                        break;
                }
            }
        }


        barChart.getData().clear(); // Vorherige Daten löschen
        barChart.getData().add(series);
    }

    public void initializeLabels() {
        int N = 100; // Gesamtanzahl der Steine im Beutel
        int n = 4;   // Anzahl der gezogenen Steine


        double einzelBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 1);
        double paarBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 2);
        double dreierBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 3);
        double viererBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 4);


        lbl_einzel_blau.setText(String.format("%.2f%%", einzelBlauProbability * 100));
        lbl_paar_blau.setText(String.format("%.2f%%", paarBlauProbability * 100));
        lbl_dreier_blau.setText(String.format("%.2f%%", dreierBlauProbability * 100));
        lbl_vierer_blau.setText(String.format("%.2f%%", viererBlauProbability * 100));


        double einzelGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 1);
        double paarGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 2);
        double dreierGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 3);
        double viererGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 4);

        lbl_einzel_gelb.setText(String.format("%.2f%%", einzelGelbProbability * 100));
        lbl_paar_gelb.setText(String.format("%.2f%%", paarGelbProbability * 100));
        lbl_dreier_gelb.setText(String.format("%.2f%%", dreierGelbProbability * 100));
        lbl_vierer_gelb.setText(String.format("%.2f%%", viererGelbProbability * 100));

        double einzelTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 1);
        double paarTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 2);
        double dreierTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 3);
        double viererTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 4);

        lbl_einzel_tuerkis.setText(String.format("%.2f%%", einzelTuerkisProbability * 100));
        lbl_paar_tuerkis.setText(String.format("%.2f%%", paarTuerkisProbability * 100));
        lbl_dreier_tuerkis.setText(String.format("%.2f%%", dreierTuerkisProbability * 100));
        lbl_vierer_tuerkis.setText(String.format("%.2f%%", viererTuerkisProbability * 100));

        double einzelRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 1);
        double paarRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 2);
        double dreierRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 3);
        double viererRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 4);

        lbl_einzel_rot.setText(String.format("%.2f%%", einzelRotProbability * 100));
        lbl_paar_rot.setText(String.format("%.2f%%", paarRotProbability * 100));
        lbl_dreier_rot.setText(String.format("%.2f%%", dreierRotProbability * 100));
        lbl_vierer_rot.setText(String.format("%.2f%%", viererRotProbability * 100));

        double einzelSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 1);
        double paarSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 2);
        double dreierSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 3);
        double viererSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, 20, n, 4);

        lbl_einzel_schwarz.setText(String.format("%.2f%%", einzelSchwarzProbability * 100));
        lbl_paar_schwarz.setText(String.format("%.2f%%", paarSchwarzProbability * 100));
        lbl_dreier_schwarz.setText(String.format("%.2f%%", dreierSchwarzProbability * 100));
        lbl_vierer_schwarz.setText(String.format("%.2f%%", viererSchwarzProbability * 100));

    }


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
        // @TODO Methode um alles in einem Call zu machen
        tileBag.removeTiles(Tile.colors.get("blue"), blueCount);
        tileBag.removeTiles(Tile.colors.get("yellow"), yellowCount);
        tileBag.removeTiles(Tile.colors.get("green"), turquoiseCount);
        tileBag.removeTiles(Tile.colors.get("red"), redCount);
        tileBag.removeTiles(Tile.colors.get("blue"), blackCount);

        //Update das Kuchendiagramm
        updatePieChart(tileBag, pieChart);

        //Update des BarCharts
        updateBarChart(tileBag, barChart);

        //Update der Labels
        updateLabels(tileBag);


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

        initializeLabels();

        initializeBarChart();
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


    //Update Methoden
    public void updatePieChart(TileBag tileBag, PieChart pieChart) {

        lbl_gesamtanzahl_fliesen.setText("Gesamtanzahl Fliesen: " + tileBag.getTotalTileCount());

        List<PieChart.Data> pieChartData = new ArrayList<>();
        pieChartData.add(new PieChart.Data("Blau: " + tileBag.getTileCount(Tile.colors.get("blue")), tileBag.getTileCount(Tile.colors.get("blue"))));
        pieChartData.add(new PieChart.Data("Gelb: " + tileBag.getTileCount(Tile.colors.get("yellow")), tileBag.getTileCount(Tile.colors.get("yellow"))));
        pieChartData.add(new PieChart.Data("Türkis: " + tileBag.getTileCount(Tile.colors.get("green")), tileBag.getTileCount(Tile.colors.get("green"))));
        pieChartData.add(new PieChart.Data("Rot: " + tileBag.getTileCount(Tile.colors.get("red")), tileBag.getTileCount(Tile.colors.get("red"))));
        pieChartData.add(new PieChart.Data("Schwarz: " + tileBag.getTileCount(Tile.colors.get("black")), tileBag.getTileCount(Tile.colors.get("black"))));

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


    public void updateBarChart(TileBag tileBag, BarChart<String, Number> barChart) {
        // Anzahl der Gesamtsteine im Beutel
        int totalTiles = tileBag.getTotalTileCount();

        // Berechnung der Wahrscheinlichkeit für jede Farbe
        double blueProbability = tileBag.calculateProbability(Tile.colors.get("blue"));
        double yellowProbability = tileBag.calculateProbability(Tile.colors.get("yellow"));
        double turquoiseProbability = tileBag.calculateProbability(Tile.colors.get("green"));
        double redProbability = tileBag.calculateProbability(Tile.colors.get("red"));
        double blackProbability = tileBag.calculateProbability(Tile.colors.get("black"));

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
                        node.setStyle("-fx-bar-fill: gray;");
                        break;
                }
            }
        }

        barChart.getData().clear(); // Vorherige Daten löschen
        barChart.getData().add(series);
    }


    public void updateLabels(TileBag tileBag) {
        int N = tileBag.getTotalTileCount(); // Gesamtanzahl der Steine im Beutel
        int n = 4;   // Anzahl der gezogenen Steine

        double einzelBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("blue")), n, 1);
        double paarBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("blue")), n, 2);
        double dreierBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("blue")), n, 3);
        double viererBlauProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("blue")), n, 4);

        lbl_einzel_blau.setText(String.format("%.2f%%", einzelBlauProbability * 100));
        lbl_paar_blau.setText(String.format("%.2f%%", paarBlauProbability * 100));
        lbl_dreier_blau.setText(String.format("%.2f%%", dreierBlauProbability * 100));
        lbl_vierer_blau.setText(String.format("%.2f%%", viererBlauProbability * 100));

        double einzelGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("yellow")), n, 1);
        double paarGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("yellow")), n, 2);
        double dreierGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("yellow")), n, 3);
        double viererGelbProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("yellow")), n, 4);

        lbl_einzel_gelb.setText(String.format("%.2f%%", einzelGelbProbability * 100));
        lbl_paar_gelb.setText(String.format("%.2f%%", paarGelbProbability * 100));
        lbl_dreier_gelb.setText(String.format("%.2f%%", dreierGelbProbability * 100));
        lbl_vierer_gelb.setText(String.format("%.2f%%", viererGelbProbability * 100));


        double einzelTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("green")), n, 1);
        double paarTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("green")), n, 2);
        double dreierTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("green")), n, 3);
        double viererTuerkisProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("green")), n, 4);

        lbl_einzel_tuerkis.setText(String.format("%.2f%%", einzelTuerkisProbability * 100));
        lbl_paar_tuerkis.setText(String.format("%.2f%%", paarTuerkisProbability * 100));
        lbl_dreier_tuerkis.setText(String.format("%.2f%%", dreierTuerkisProbability * 100));
        lbl_vierer_tuerkis.setText(String.format("%.2f%%", viererTuerkisProbability * 100));

        double einzelRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("red")), n, 1);
        double paarRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("red")), n, 2);
        double dreierRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("red")), n, 3);
        double viererRotProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("red")), n, 4);

        lbl_einzel_rot.setText(String.format("%.2f%%", einzelRotProbability * 100));
        lbl_paar_rot.setText(String.format("%.2f%%", paarRotProbability * 100));
        lbl_dreier_rot.setText(String.format("%.2f%%", dreierRotProbability * 100));
        lbl_vierer_rot.setText(String.format("%.2f%%", viererRotProbability * 100));

        double einzelSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("black")), n, 1);
        double paarSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("black")), n, 2);
        double dreierSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("black")), n, 3);
        double viererSchwarzProbability = HypergeometricDistribution.hypergeometricDistribution(N, tileBag.getTileCount(Tile.colors.get("black")), n, 4);

        lbl_einzel_schwarz.setText(String.format("%.2f%%", einzelSchwarzProbability * 100));
        lbl_paar_schwarz.setText(String.format("%.2f%%", paarSchwarzProbability * 100));
        lbl_dreier_schwarz.setText(String.format("%.2f%%", dreierSchwarzProbability * 100));
        lbl_vierer_schwarz.setText(String.format("%.2f%%", viererSchwarzProbability * 100));


    }


}
