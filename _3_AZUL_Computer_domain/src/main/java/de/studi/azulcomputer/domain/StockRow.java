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

    public void addTile(Tile tile) {
        storage.add(tile);
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
