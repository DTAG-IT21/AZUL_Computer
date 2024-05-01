package de.studi.azulcomputer.domain;

import java.util.LinkedList;

public class Manufacture implements TileStore {
    public static final int size = 4;
    private final Middle middle;
    LinkedList<Tile> tiles = new LinkedList<>();

    public Manufacture(Middle middle) {
        this.middle = middle;
    }

    public Manufacture(Manufacture manufacture) {
        this.middle = manufacture.middle;
        tiles.addAll(manufacture.tiles);
    }

    public void load(LinkedList<Tile> tiles) {
        // @TODO Magic Number
        if (tiles.size() != 4) {
            System.out.println("Error: Tile array length is incorrect");
        } else {
            this.tiles = tiles;
        }
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
        LinkedList<Tile> picked = new LinkedList<>();
        LinkedList<Tile> discard = new LinkedList<>();

        for (Tile tile : tiles) {
            if (tile.getColor() == color) {
                picked.add(tile);
            } else {
                discard.add(tile);
            }
        }

        middle.load(discard);
        tiles.clear();
        return picked;
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public LinkedList<Tile> getTiles() {
        return tiles;
    }

    public int getSize() {
        return size;
    }
}
