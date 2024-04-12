package de.studi.azulcomputer.backend;

import java.util.Arrays;

public class Mosaic {
    public static int[][] colorPattern = new int[][]{
            {0, 1, 2, 3, 4},
            {1, 2, 3, 4, 0},
            {2, 3, 4, 0, 1},
            {3, 4, 0, 1, 2},
            {4, 0, 1, 2, 3}
    };
    private final Tile[][] pattern = new Tile[5][5];

    public Mosaic() {
    }

    public static int getColumn(int row, Tile tile) {
        int column = 0;
        // @TODO Magic Number

        while (column < 5) {
            if (colorPattern[row][column] == tile.getColor()) {
                return column;
            }
            column++;
        }
        return -1;
    }

    public Tile[][] getPattern() {
        return this.pattern;
    }

    public void placeTile(int row, Tile tile) throws IllegalMoveException {
        if (isLegalMove(row, tile)) {
            int column = getColumn(row, tile);
            this.pattern[row][column] = tile;
        } else {
            throw new IllegalMoveException("Das ausgewählte Feld ist bereits besetzt");
        }
    }

    public void reset() {
        for (Tile[] tiles : this.pattern) {
            Arrays.fill(tiles, null);
        }
    }

    public int potentialScore(int row, Tile tile) {

        // Check if field is already set
        if (!isLegalMove(row, tile)) {
            return 0;
        }

        // Set field, evaluate score and unset field
        int column = getColumn(row, tile);
        this.pattern[row][column] = tile;
        int result = ScoreCalculator.moveEval(this.pattern, row, tile);
        this.pattern[row][column] = null;

        return result;
    }

    public boolean isLegalMove(int row, Tile tile) {
        int column = getColumn(row, tile);
        return pattern[row][column] == null;
    }
}


