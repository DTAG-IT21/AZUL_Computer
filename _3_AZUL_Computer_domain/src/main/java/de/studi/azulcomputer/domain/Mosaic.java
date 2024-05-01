package de.studi.azulcomputer.domain;

import java.util.Arrays;

public class Mosaic {

    public static final int mosaicSize = 5;

    public static int[][] colorPattern = new int[][]{
            {0, 1, 2, 3, 4},
            {4, 0, 1, 2, 3},
            {3, 4, 0, 1, 2},
            {2, 3, 4, 0, 1},
            {1, 2, 3, 4, 0}
    };
    private final Tile[][] pattern;

    public Mosaic() {
        pattern = new Tile[mosaicSize][mosaicSize];
    }

    public Mosaic(Mosaic mosaic) {
        pattern = new Tile[mosaicSize][mosaicSize];
        for (int i = 0; i < mosaicSize; i++) {
            System.arraycopy(mosaic.pattern[i], 0, pattern[i], 0, mosaicSize);
        }
    }

    public static int getColumn(int row, int color) {
        int column = 0;

        while (column < mosaicSize) {
            if (colorPattern[row][column] == color) {
                return column;
            }
            column++;
        }
        return -1;
    }

    public Tile[][] getPattern() {
        return this.pattern;
    }

    public void placeTile(int row, Tile tile) {
        int column = getColumn(row, tile.getColor());
        this.pattern[row][column] = tile;
    }

    public void reset() {
        for (Tile[] tiles : this.pattern) {
            Arrays.fill(tiles, null);
        }
    }
}


