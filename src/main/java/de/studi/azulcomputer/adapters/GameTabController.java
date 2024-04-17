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
        createMosaic(mosaic);
        createStorage(storage);
        createFloor(floor);
        createManufacture(manufacture1);
        createManufacture(manufacture2);
        createManufacture(manufacture3);
        createManufacture(manufacture4);
        createManufacture(manufacture5);
        createCenter(center);
    }

    private void createMosaic(GridPane grid){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50); // Button-Größe anpassen
                grid.add(button, j, i);
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

    private void createFloor(GridPane grid){
        for (int i = 0; i < 7; i++) {
            Button button = new Button();
            button.setPrefSize(50, 50);
            grid.add(button, i, 0);
        }
    }
    
    private void createManufacture(GridPane grid){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                grid.add(button, j, i);
            }
        }
    }

    private void createCenter(GridPane grid){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                grid.add(button, j, i);
            }
        }
    }

    // @TODO add connection to backend
}