package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class Middle {
    public LinkedList<Tile> tiles = new LinkedList<>();

    public Middle() {}

    public void add(LinkedList<Tile> tiles) {
        this.tiles.addAll(tiles);
    }

    public LinkedList<Tile> pick(int color){
        LinkedList<Tile> picked = new LinkedList<>();

        for (Tile tile : tiles) {
            if(tile.getColor() == color){
                picked.add(tile);
                tiles.remove(tile);
            }
        }
        return picked;
    }
}
