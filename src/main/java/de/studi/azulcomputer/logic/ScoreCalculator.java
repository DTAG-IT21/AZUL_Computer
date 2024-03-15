package de.studi.azulcomputer.logic;

import javafx.scene.control.Button;

public class ScoreCalculator {



    public static int totalScore;
    //Methode zur Berechnung der aktuell möglichen Punkte und dem Aktualisieren der Texte der Felder entsprechend
    public static void updateButtonText(Button[][] buttonGrid, int[][] binaryGrid) {
        int totalPoints = 0;

        // Durchlauf aller Felder im Button-Grid
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                Button button = buttonGrid[i][j];
                int horizontalPoints = calculateHorizontalPoints(binaryGrid, i, j);
                int verticalPoints = calculateVerticalPoints(binaryGrid, i, j);

                // Füge 1 Punkt hinzu, wenn eine Fliese gelegt wurde
                if (binaryGrid[i][j] == 1) {
                    horizontalPoints++;
                    verticalPoints++;
                }

                // Summieren Sie die horizontalen und vertikalen Punkte und addiere 1 für die Fliese selbst
                totalPoints = horizontalPoints + verticalPoints + 1;

                // 2. mal +1 wenn der Stein sowohl eine Horizontale als auch eine Vertikale ergänzt
                if(horizontalPoints!=0 && verticalPoints!=0){
                    totalPoints++;
                }

                // Überprüfen Sie, ob ein Stein auf diesem Feld platziert wurde (binaryGrid-Wert == 1)
                if (binaryGrid[i][j] == 1) {
                    button.setText("X"); // Setze "X" als Text auf dem Button, wenn Stein gelegt wurde
                    button.setDisable(true); // Deaktiviere den Button, um weitere Platzierungen zu verhindern
                } else {
                    button.setText("" + totalPoints); // Ansonsten zeige die möglichen Punkte an
                }
            }

        }



    }

    //Methode zur Berechnung der Horizontalen gesamtpunkte
    private static int calculateHorizontalPoints(int[][] binaryGrid, int row, int col) {
        int horizontalPoints = 0;

        // Überprüfen Sie die angrenzenden Steine horizontal
        int leftIndex = col - 1;
        int rightIndex = col + 1;
        //Summierung der Punkte links der Fliese
        while (leftIndex >= 0 && binaryGrid[row][leftIndex] == 1) {
            horizontalPoints++;
            leftIndex--;
        }
        //Summierung der Punkte rechts der Fliese
        while (rightIndex < binaryGrid[row].length && binaryGrid[row][rightIndex] == 1) {
            horizontalPoints++;
            rightIndex++;
        }

        //Rückgabe der Punkte für horizontale Reihe
        return horizontalPoints;
    }

    //Methode zur Berechnung der vertikalen gesamtpunkte
    private static int calculateVerticalPoints(int[][] binaryGrid, int row, int col) {
        int verticalPoints = 0;

        // Überprüfen Sie die angrenzenden Steine vertikal
        int topIndex = row - 1;
        int bottomIndex = row + 1;

        //Summierung der Punkte oberhalb der Fliese
        while (topIndex >= 0 && binaryGrid[topIndex][col] == 1) {
            verticalPoints++;
            topIndex--;
        }

        //Summierung der Punkte unterhalb der Fliese
        while (bottomIndex < binaryGrid.length && binaryGrid[bottomIndex][col] == 1) {
            verticalPoints++;
            bottomIndex++;
        }

        //Rückgabe der Punkte für vertikale Reihe
        return verticalPoints;
    }

    // Methode zur Aktualisierung der Gesamtpunktzahl
    public static void updateTotalScore(int totalPoints) {
        totalScore+= totalPoints;
    }


    public static int getTotalScore(){
        return totalScore;
    }
}
