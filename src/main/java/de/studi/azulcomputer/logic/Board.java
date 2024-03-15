package de.studi.azulcomputer.logic;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Board{

// Methode zur aktualisierung der Button Farbe zur Repräsentation, ob ein stein gelegt wurde oder nicht
        public void updateButtonColor(Button button, int[][] binaryGrid, int row, int col) {
        // Überprüfung der aktuellen Zustände im BinaryGrid
        System.out.println("Binary Grid Status:");
        System.out.println(Arrays.deepToString(binaryGrid));

        //Stein nicht gelegt(Button nicht geklicked)
        if (binaryGrid[row][col] == 1) {
            lightenButtonColor(button);
            //ist der Stein gelegt(Button geklicked)
        } else {
            darkenButtonColor(button);
        }
    }

    private void darkenButtonColor(Button button) {
        // Aktuelle Hintergrundfarbe des Buttons abrufen
        Color currentColor = (Color) button.getBackground().getFills().get(0).getFill();

        // Überprüfen, ob die aktuelle Farbe bereits schwarz ist
        if (currentColor.equals(Color.BLACK)) {
            // Hintergrundfarbe aufhellen (in ein Grauton)
            Color newColor = Color.LIGHTGRAY; // Neue Farbe als Grauton

            // Neue Hintergrundfarbe setzen
            button.setStyle("-fx-background-color: " + toRGBCode(newColor) + ";");
        } else {
            // Hintergrundfarbe verdunkeln
            Color newColor = currentColor.darker().darker(); // Farbe verdunkeln

            // Neue Hintergrundfarbe setzen
            button.setStyle("-fx-background-color: " + toRGBCode(newColor) + ";");
        }
    }

    // Methode zur Konvertierung einer JavaFX-Farbe in einen RGB-Code
    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }




    private void lightenButtonColor(Button button) {
        // Aktuelle Hintergrundfarbe des Buttons abrufen
        Color currentColor = (Color) button.getBackground().getFills().get(0).getFill();

        // Überprüfen, ob die aktuelle Farbe grau ist
        if (currentColor.equals(Color.LIGHTGRAY)) {
            // Hintergrundfarbe auf Schwarz setzen
            button.setStyle("-fx-background-color: black;");
        } else {
            // Hintergrundfarbe aufhellen
            Color newColor = currentColor.brighter().brighter(); // Farbe aufhellen

            // Neue Hintergrundfarbe setzen
            button.setStyle("-fx-background-color: " + toRGBCode(newColor) + ";");
        }
    }

    //Zustand im Binary Grid aktualisieren, wenn ein button geklicked wurde
    public void toggleButtonState(Button button, int[][] binaryGrid, int row, int col) {
        // Aktuellen Zustand des Buttons erhalten
        int currentState = binaryGrid[row][col];

        // Wechseln des Zustands des Buttons
        int newState = (currentState == 0) ? 1 : 0;
        binaryGrid[row][col] = newState;

        // Aktualisieren der Hintergrundfarbe basierend auf dem neuen Zustand
        updateButtonColor(button,binaryGrid, row, col);
    }



    // Rücksetzen von ButtonGrid und BinaryGrid
    public void resetButtonGrid(Button[][] buttonGrid, int[][] binaryGrid) {
        // Durchlaufen Sie alle Felder im Button-Grid
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                Button button = buttonGrid[i][j];
                button.setDisable(false); // Stellen Sie den Button auf klickbar zurück
                button.setText("1"); // Setzen Sie den Text auf "1"
                setButtonOriginalColor(button, i, j); // Setzen Sie die Farbe auf den ursprünglichen Stil zurück
                binaryGrid[i][j] = 0; // Setzen Sie das binaryGrid zurück auf 0
            }
        }
    }

    // Rücksetzen der Buttons auf ursprüngliche Farbe basierend auf Position
    private void setButtonOriginalColor(Button button, int row, int col) {
        // Bestimmen Sie die ursprüngliche Farbe basierend auf der Position des Buttons
        String colorValue = getOriginalColor(row, col);
        button.setStyle("-fx-background-color: " + colorValue + ";");
    }


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
}


