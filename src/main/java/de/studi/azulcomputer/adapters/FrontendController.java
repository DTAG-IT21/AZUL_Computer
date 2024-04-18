package de.studi.azulcomputer.adapters;

public class FrontendController {

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

}
