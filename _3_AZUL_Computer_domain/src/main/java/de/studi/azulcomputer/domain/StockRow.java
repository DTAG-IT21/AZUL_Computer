package de.studi.azulcomputer.domain;

import java.util.LinkedList;

public class StockRow {
    private final int maxTiles;
    private final LinkedList<Tile> storage = new LinkedList<>();

    public StockRow(int maxTiles) {
        this.maxTiles = maxTiles;
    }

    public int getMaxTiles() {
        return maxTiles;
    }

    public void addTile(Tile tile) throws IllegalMoveException {
        if (storage.size() < maxTiles && (storage.isEmpty() || tile.getColor() == storage.getFirst().getColor())) {
            storage.add(tile);
        } else {
            throw new IllegalMoveException("Tile can not be placed in this row");
        }
    }

    public Tile getFirst() {
        if (storage.isEmpty()) {
            return null;
        }
        return storage.getFirst();
    }

    public int getCurrentCount() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }
}
