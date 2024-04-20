package de.studi.azulcomputer.adapters;

import de.studi.azulcomputer.backend.Game;
import de.studi.azulcomputer.backend.Tile;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class GameTabController {

    public static final int PLAYER_COUNT = 2;

    @FXML
    private GridPane mosaic1;

    @FXML
    private GridPane storage1;

    @FXML
    private GridPane floor1;

    @FXML
    private GridPane mosaic2;

    @FXML
    private GridPane storage2;

    @FXML
    private GridPane floor2;

    @FXML
    private GridPane manufacture1;

    @FXML
    private GridPane manufacture2;

    @FXML
    private GridPane manufacture3;

    @FXML
    private GridPane manufacture4;

    @FXML
    private GridPane manufacture5;

    @FXML
    private GridPane center;

    private final LinkedList<GridPane> grids = new LinkedList<>();

    private final Game game = new Game();

    public void initialize() {
        // Add all grids to list
        grids.add(mosaic1);
        grids.add(mosaic2);
        grids.add(storage1);
        grids.add(storage2);
        grids.add(floor1);
        grids.add(floor2);
        grids.add(center);
        grids.add(manufacture1);
        grids.add(manufacture2);
        grids.add(manufacture3);
        grids.add(manufacture4);
        grids.add(manufacture5);
        // Create all buttons
        createButtons(mosaic1, 5, 5);
        createStorage(storage1);
        createButtons(floor1, 1, 7);
        createButtons(mosaic2, 5, 5);
        createStorage(storage2);
        createButtons(floor2, 1, 7);
        createButtons(manufacture1, 2, 2);
        createButtons(manufacture2, 2, 2);
        createButtons(manufacture3, 2, 2);
        createButtons(manufacture4, 2, 2);
        createButtons(manufacture5, 2, 2);
        createButtons(center, 4, 4);

        for (int i = 0; i < grids.size(); i++) {
            createButtonListeners(grids.get(i), i);
        }

        update();
    }

    private void createButtons(GridPane grid, int rows, int cols) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                grid.add(button, i, j);
            }
        }
    }

    private void createStorage(GridPane grid){
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                grid.add(button, 4 - i, j);
            }
        }
    }
    // @TODO add connection to backend

    private void createButtonListeners(GridPane grid, int gridIndex) {
        for (Node button : grid.getChildren()){
            button.setOnMouseClicked(event -> handleButtonClick(button, gridIndex));
        }
    }

    private void handleButtonClick(Node button, int gridIndex) {
        if (button instanceof Button b){
            int col = GridPane.getColumnIndex(b);
            int row = GridPane.getRowIndex(b);

            String grid = switch (gridIndex) {
                case 0 -> "mosaic1";
                case 1 -> "mosaic2";
                case 2 -> "storage1";
                case 3 -> "storage2";
                case 4 -> "floor1";
                case 5 -> "floor2";
                case 6 -> "manufacture1";
                case 7 -> "manufacture2";
                case 8 -> "manufacture3";
                case 9 -> "manufacture4";
                case 10 -> "manufacture5";
                case 11 -> "center";
                default -> "";
            };

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This is " + grid + " row " + row + " col " + col);
            alert.showAndWait();
        }
    }

    public void update(){
        LinkedList<Integer> colors = new LinkedList<>();
        colors.addAll(game.mosaicColors());
        colors.addAll(game.stockColors());
        colors.addAll(game.floorColors());
        colors.addAll(game.manufactureColors(0));
        colors.addAll(game.manufactureColors(1));
        colors.addAll(game.manufactureColors(2));
        colors.addAll(game.manufactureColors(3));
        colors.addAll(game.manufactureColors(4));
        colors.addAll(game.manufactureColors(5));

        int colorIndex = 0;
        for (int i = 0; i < 12; i++) {
            GridPane grid = grids.get(i);
            for (Node node : grid.getChildren()) {
                if (node instanceof Button b) {
                    if (colors.get(colorIndex) != -1) {
                        b.setStyle("-fx-background-color: " + Tile.intToColor.get(colors.get(colorIndex)));
                    }else{
                        b.setStyle("");
                    }
                }
                colorIndex++;
            }
        }
    }
}