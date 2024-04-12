package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class Middle implements TileStore {
    public LinkedList<Tile> tiles = new LinkedList<>();

    public Middle() {

    }

    public void load(LinkedList<Tile> tiles) {
        this.tiles.addAll(tiles);
    }

    public LinkedList<Tile> pick(int color) {
        LinkedList<Tile> picked = new LinkedList<>();

        for (Tile tile : tiles) {
            if (tile.getColor() == color) {
                picked.add(tile);
                tiles.remove(tile);
            }
        }
        return picked;
    }


    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public void addGameStone() {
        tiles.add(new Tile(Tile.colors.get("gameStone")));
    }
}
