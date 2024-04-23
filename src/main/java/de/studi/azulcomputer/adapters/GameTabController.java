package de.studi.azulcomputer.adapters;

import de.studi.azulcomputer.backend.Game;
import de.studi.azulcomputer.backend.IllegalMoveException;
import de.studi.azulcomputer.backend.Listener;
import de.studi.azulcomputer.backend.Tile;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class GameTabController implements Listener {

    public static final int PLAYER_COUNT = 2;
    public static final int MOSAIC_INDEX = 0;
    public static final int STOCK_INDEX = 2;
    public static final int FLOOR_INDEX = 4;
    public static final int MANUFACTURE_INDEX = 6;

    public static int currentManufacture;
    public static int currentColor;

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

    private LinkedList<Button> selectedTiles = new LinkedList<>();

    private LinkedList<Integer> storeRows = new LinkedList<>();

    private Game game;

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

        game = new Game();
        game.subscribe(this);
    }

    private void createButtons(GridPane grid, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                grid.add(button, j, i);
            }
        }
    }

    private void createStorage(GridPane grid){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                grid.add(button, 4 - j, i);
            }
        }
    }

    private void createButtonListeners(GridPane grid, int gridIndex) {
        for (Node button : grid.getChildren()){
            button.setOnMouseClicked(event -> handleButtonClick(button, gridIndex));
        }
    }

    private void handleButtonClick(Node button, int gridIndex) {
        if (button instanceof Button b){
            int col = GridPane.getColumnIndex(b);
            int row = GridPane.getRowIndex(b);

            switch (gridIndex) {
                case 2, 3,4 ,5 : stockClick(button, gridIndex); break;
                case 6, 7, 8, 9, 10, 11: manufactureClick(button, gridIndex); break;
                default : break;
            };
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
                        if (selectedTiles.contains(b)) {
                            b.setStyle("-fx-background-color: " + Tile.intToColor.get(colors.get(colorIndex)) + "; -fx-border-color: orange; -fx-border-width: 3; -fx-border-style: solid;");
                        }else{
                            b.setStyle("-fx-background-color: " + Tile.intToColor.get(colors.get(colorIndex)));
                        }
                    }else{
                        b.setStyle("");
                    }
                }
                colorIndex++;
            }
        }
    }

    public void manufactureClick(Node button, int gridIndex){
        if(button instanceof Button b){
            currentManufacture = gridIndex - MANUFACTURE_INDEX;
            GridPane grid = grids.get(gridIndex);
            int col = GridPane.getColumnIndex(b);
            int row = GridPane.getRowIndex(b);
            int tileIndex = 0;
            if (currentManufacture == 0){
                tileIndex = (row * 4) + col;
            }else{
                tileIndex = (row * 2) + col;
            }

            LinkedList<Integer> tileColors = game.manufactureColors(currentManufacture);
            currentColor = tileColors.get(tileIndex);

            LinkedList<Button> buttons = new LinkedList<>();
            for (int i = 0; i < tileColors.size(); i++) {
                if (tileColors.get(i) == currentColor) {
                    if (grid.getChildren().get(i) instanceof Button bt) {
                        buttons.add(bt);
                    }
                }
            }
            selectedTiles = buttons;
        }
        update();
    }

    public void stockClick(Node button, int gridIndex){
        if (button instanceof Button b){
            int row = 0;
            if (gridIndex == STOCK_INDEX || gridIndex == STOCK_INDEX + 1){
                row = GridPane.getRowIndex(b);
            }else{
                row = -1;
            }

            storeRows.add(row);

            if(selectedTiles.size() == storeRows.size()){
                try {
                    game.pick(currentManufacture, currentColor, storeRows);
                } catch (IllegalMoveException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    alert.setHeaderText("Illegal Move");
                    alert.showAndWait();
                }finally {
                    currentManufacture = -1;
                    currentColor = -1;
                    selectedTiles.clear();
                    storeRows.clear();
                    update();
                }
            }
        }
    }
}