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

    public boolean addTile(Tile tile) {
        if (storage.size() < maxTiles) {
            storage.add(tile);
            return true;
        }else {
            return false;
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
