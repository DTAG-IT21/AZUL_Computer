package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public interface TileStore {

    void load(LinkedList<Tile> tiles);

    LinkedList<Tile> pick(int color);

    LinkedList<Tile> getTiles();

    boolean isEmpty();

    int getSize();
}
