package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class StockRow {
    private LinkedList<Tile> storage = new LinkedList<>();
    private final int maxTiles;

    public StockRow(int maxTiles) {
        this.maxTiles = maxTiles;
    }

    public int getMaxTiles() {
        return maxTiles;
    }

    public void addTile(Tile tile) throws IllegalMoveException {
        if (storage.size() < maxTiles && (storage.isEmpty() || tile.getColor() == storage.getFirst().getColor())) {
            storage.add(tile);
        }else {
            throw new IllegalMoveException("Tile can not be placed in this row");
        }
    }

    public int getCurrentColor(){
        if (storage.isEmpty()) {
            return -1;
        }
        return storage.getFirst().getColor();
    }

    public int getCurrentCount(){
        return storage.size();
    }

    public void clear(){
        storage.clear();
    }
}
