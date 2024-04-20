package de.studi.azulcomputer.adapters;

import de.studi.azulcomputer.backend.Game;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class GameTabController {

    @FXML
    private GridPane mosaic;

    @FXML
    private GridPane storage;

    @FXML
    private GridPane floor;

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
        grids.add(mosaic);
        grids.add(storage);
        grids.add(floor);
        grids.add(manufacture1);
        grids.add(manufacture2);
        grids.add(manufacture3);
        grids.add(manufacture4);
        grids.add(manufacture5);
        grids.add(center);
        // Create all buttons
        createButtons(mosaic, 5, 5);
        createStorage(storage);
        createButtons(floor, 1, 7);
        createButtons(manufacture1, 2, 2);
        createButtons(manufacture2, 2, 2);
        createButtons(manufacture3, 2, 2);
        createButtons(manufacture4, 2, 2);
        createButtons(manufacture5, 2, 2);
        createButtons(center, 4, 4);

        for (int i = 0; i < grids.size(); i++) {
            createButtonListeners(grids.get(i), i);
        }
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
                case 0 -> "mosaic";
                case 1 -> "storage";
                case 2 -> "floor";
                case 3 -> "manufacture1";
                case 4 -> "manufacture2";
                case 5 -> "manufacture3";
                case 6 -> "manufacture4";
                case 7 -> "manufacture5";
                case 8 -> "center";
                default -> "";
            };

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This is " + grid + " row " + row + " col " + col);
            alert.showAndWait();
        }
    }


}