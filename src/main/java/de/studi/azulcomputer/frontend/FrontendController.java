package de.studi.azulcomputer.frontend;

import de.studi.azulcomputer.backend.Mosaic;
import de.studi.azulcomputer.backend.HypergeometricDistribution;
import de.studi.azulcomputer.backend.IllegalMoveException;
import de.studi.azulcomputer.backend.Tile;
import de.studi.azulcomputer.backend.TileBag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FrontendController {

    //Variablen initialisierung

    private final TileBag tileBag = new TileBag(); // Instanzvariable tileBag erstellen und initialisieren
    public Mosaic mosaic = new Mosaic();
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
    private Label lbl_TotalScore;
    @FXML
    private Button btn_brd_00;
    @FXML
    private Button btn_brd_01;
    @FXML
    private Button btn_brd_02;
    @FXML
    private Button btn_brd_03;
    @FXML
    private Button btn_brd_04;
    @FXML
    private Button btn_brd_10;
    @FXML
    private Button btn_brd_11;
    @FXML
    private Button btn_brd_12;
    @FXML
    private Button btn_brd_13;
    @FXML
    private Button btn_brd_14;
    @FXML
    private Button btn_brd_20;
    @FXML
    private Button btn_brd_21;
    @FXML
    private Button btn_brd_22;
    @FXML
    private Button btn_brd_23;
    @FXML
    private Button btn_brd_24;
    @FXML
    private Button btn_brd_30;
    @FXML
    private Button btn_brd_31;
    @FXML
    private Button btn_brd_32;
    @FXML
    private Button btn_brd_33;
    @FXML
    private Button btn_brd_34;
    @FXML
    private Button btn_brd_40;
    @FXML
    private Button btn_brd_41;
    @FXML
    private Button btn_brd_42;
    @FXML
    private Button btn_brd_43;
    @FXML
    private Button btn_brd_44;
    private Button[][] buttonGrid;

    // Refactoring potential lesbarkeit mit farbennamen statt #...
    // Ursprüngliche Farben der Buttons zur Repräsentation des Spielfelds
    private String getOriginalColor(int row, int col) {
        String[][] originalColors = {
                {"#00007C", "#7C5000", "#7C0000", "#0C0C0C", "#237C73"},
                {"#237C73", "#00007C", "#7C5000", "#7C0000", "#0C0C0C"},
                {"#0C0C0C", "#237C73", "#00007C", "#7C5000", "#7C0000"},
                {"#7C0000", "#0C0C0C", "#237C73", "#00007C", "#7C5000"},
                {"#7C5000", "#7C0000", "#0C0C0C", "#237C73", "#00007C"}
        };

        return originalColors[row][col];
    }

    // Initialisierungsmethode
    @FXML
    public void initialize() {

        initializeButtonGrid();

        initializeChoiceBoxes();

        initializePieChart();

        initializeBarChart();

        initializeLabels();

    }


    // Hilfsmethoden zur initialisierung
    // Potential Code smells ?! Don´t repeat yourself (Refactoring)
    private void initializeButtonGrid() {
        buttonGrid = new Button[][]{
                {btn_brd_00, btn_brd_01, btn_brd_02, btn_brd_03, btn_brd_04},
                {btn_brd_10, btn_brd_11, btn_brd_12, btn_brd_13, btn_brd_14},
                {btn_brd_20, btn_brd_21, btn_brd_22, btn_brd_23, btn_brd_24},
                {btn_brd_30, btn_brd_31, btn_brd_32, btn_brd_33, btn_brd_34},
                {btn_brd_40, btn_brd_41, btn_brd_42, btn_brd_43, btn_brd_44}
        };

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


        lbl_TotalScore.setText("0");

    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    //Buttonhandler Methoden
    @FXML
    public void handleBoardButtonClick(ActionEvent event) {

        // Get button object
        Object source = event.getSource();
        if (source instanceof Button button) {

            // Get row and column
            int row = GridPane.getRowIndex(button);
            int column = GridPane.getColumnIndex(button);

            // Place tile on board; Show Error message if move is illegal
            try {
                mosaic.placeTile(row, new Tile(Mosaic.colorPattern[row][column]));
            } catch (IllegalMoveException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Ungültiger Zug");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

            // Update score
            lbl_TotalScore.setText(Integer.toString(mosaic.getScore()));

            // Adjust color of button
            Color buttonColor = (Color) button.getBackground().getFills().get(0).getFill();
            button.setStyle("-fx-background-color: " + toRGBCode(buttonColor.brighter().brighter()));
        }

        for (Button[] buttonList : buttonGrid) {
            for (Button button : buttonList) {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                button.setText(Integer.toString(mosaic.potentialScore(row, new Tile(Mosaic.colorPattern[row][column]))));
            }
        }
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
    public void resetButtonGrid() {
        mosaic.reset();
        lbl_TotalScore.setText(Integer.toString(mosaic.getScore()));
        for (Button[] buttonList : buttonGrid) {
            for (Button button : buttonList) {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                button.setText("1");
            }
        }
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


    //Dialog Methoden
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
