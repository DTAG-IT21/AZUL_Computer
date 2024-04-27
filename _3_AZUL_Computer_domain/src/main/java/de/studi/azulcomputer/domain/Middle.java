package de.studi.azulcomputer.domain;

import java.util.LinkedList;

public class Middle implements TileStore {
    public static final int size = 16;
    public LinkedList<Tile> tiles = new LinkedList<>();

    public Middle() {
    }

    public void load(LinkedList<Tile> tiles) {
        this.tiles.addAll(tiles);
    }

    public LinkedList<Tile> getTilesColor(int color) {
        LinkedList<Tile> picked = new LinkedList<>();

        for (Tile tile : tiles) {
            if (tile.getColor() == color) {
                picked.add(tile);
            }
        }
        return picked;
    }

    public LinkedList<Tile> pick(int color) {
        LinkedList<Tile> picked = getTilesColor(color);
        tiles.removeAll(picked);
        return picked;
    }


    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public void addGameStone() {
        tiles.add(new Tile(Tile.getIntColor("gameStone")));
    }

    public LinkedList<Tile> getTiles() {
        return tiles;
    }

    public int getSize() {
        return size;
    }
}
