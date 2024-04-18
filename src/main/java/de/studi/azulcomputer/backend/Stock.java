package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class Stock {

    // @TODO Magic Number
    private final StockRow[] stock = new StockRow[5];
    private final LinkedList<Tile> basement = new LinkedList<>();

    public Stock() {
        for (int i = 0; i < stock.length; i++) {
            stock[i] = new StockRow(i + 1);
        }
    }

    // Stores given tiles in specified row of stock
    public void store(Tile tile, int row) throws IllegalMoveException {
        if (row >= 0) {
            stock[row].addTile(tile);
        } else {
            basement.add(tile);
        }
    }

    // Delivers all full rows in stock
    public LinkedList<Integer> getFullRows() {
        LinkedList<Integer> fullRows = new LinkedList<>();

        for (StockRow row : stock) {
            if (row.getCurrentCount() == row.getMaxTiles()) {
                fullRows.add(row.getMaxTiles() - 1);
            }
        }

        return fullRows;
    }

    // Clears specified row of stock
    public void clearRow(int row) {
        if (row >= 0) {
            stock[row].clear();
        } else {
            basement.clear();
        }
    }

    public Tile getFirst(int row) {
        if (row >= 0) {
            return stock[row].getFirst();
        } else {
            return basement.getFirst();
        }
    }

    public LinkedList<Tile> getBasement() {
        return basement;
    }

    public void reset() {
        for (StockRow row : stock) {
            row.clear();
        }
        basement.clear();
    }
}
