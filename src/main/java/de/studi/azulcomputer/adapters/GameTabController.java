package de.studi.azulcomputer.adapters;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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

    public void initialize() {
        // Hier kannst du die Buttons für jedes Feld erstellen und sie den entsprechenden GridPanes hinzufügen
        createGrid(mosaic, 5, 5);
        createStorage(storage);
        createGrid(floor, 1, 7);
        createGrid(manufacture1, 2, 2);
        createGrid(manufacture2, 2, 2);
        createGrid(manufacture3, 2, 2);
        createGrid(manufacture4, 2, 2);
        createGrid(manufacture5, 2, 2);
        createGrid(center, 4, 4);
    }

    private void createGrid(GridPane grid, int rows, int cols) {
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
}