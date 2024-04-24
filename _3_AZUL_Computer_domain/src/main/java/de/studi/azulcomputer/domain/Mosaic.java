package de.studi.azulcomputer.domain;

import java.util.Arrays;

public class Mosaic {
    public static int[][] colorPattern = new int[][]{
            {0,1,2,3,4},
            {4,0,1,2,3},
            {3,4,0,1,2},
            {2,3,4,0,1},
            {1,2,3,4,0}
    };
    private final Tile[][] pattern;

    public Mosaic() {
        pattern = new Tile[5][5];
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
        int column = getColumn(row, tile);
        this.pattern[row][column] = tile;
    }

    public void reset() {
        for (Tile[] tiles : this.pattern) {
            Arrays.fill(tiles, null);
        }
    }
}


