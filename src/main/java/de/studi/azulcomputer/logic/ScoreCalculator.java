package de.studi.azulcomputer.logic;

import javafx.scene.control.Button;

public class ScoreCalculator {

    public static void updateButtonText(Button[][] buttonGrid, int[][] binaryGrid) {
        // Durchlaufen Sie alle Felder im Button-Grid
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                Button button = buttonGrid[i][j];
                int horizontalPoints = calculateHorizontalPoints(binaryGrid, i, j);
                int verticalPoints = calculateVerticalPoints(binaryGrid, i, j);

                // Fügen Sie 1 Punkt hinzu, wenn das Feld nicht leer ist
                if (binaryGrid[i][j] == 1) {
                    horizontalPoints++;
                    verticalPoints++;
                }

                // Summieren Sie die horizontalen und vertikalen Punkte
                int totalPoints = horizontalPoints + verticalPoints + 1;

                // Überprüfen Sie, ob ein Stein auf diesem Feld platziert wurde (binaryGrid-Wert == 1)
                if (binaryGrid[i][j] == 1) {
                    button.setText("X"); // Setzen Sie "X" als Text auf dem Button
                    button.setDisable(true); // Deaktivieren Sie den Button, um weitere Platzierungen zu verhindern
                } else {
                    button.setText("" + totalPoints); // Ansonsten zeigen Sie die Gesamtpunkte an
                }
            }
        }
    }

    private static int calculateHorizontalPoints(int[][] binaryGrid, int row, int col) {
        int horizontalPoints = 0;

        // Überprüfen Sie die angrenzenden Steine horizontal
        int leftIndex = col - 1;
        int rightIndex = col + 1;
        while (leftIndex >= 0 && binaryGrid[row][leftIndex] == 1) {
            horizontalPoints++;
            leftIndex--;
        }
        while (rightIndex < binaryGrid[row].length && binaryGrid[row][rightIndex] == 1) {
            horizontalPoints++;
            rightIndex++;
        }

        return horizontalPoints;
    }

    private static int calculateVerticalPoints(int[][] binaryGrid, int row, int col) {
        int verticalPoints = 0;

        // Überprüfen Sie die angrenzenden Steine vertikal
        int topIndex = row - 1;
        int bottomIndex = row + 1;
        while (topIndex >= 0 && binaryGrid[topIndex][col] == 1) {
            verticalPoints++;
            topIndex--;
        }
        while (bottomIndex < binaryGrid.length && binaryGrid[bottomIndex][col] == 1) {
            verticalPoints++;
            bottomIndex++;
        }

        return verticalPoints;
    }
}
