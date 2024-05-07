package de.studi.azulcomputer.domain;

import java.util.LinkedList;

public interface TileStore {

    void load(LinkedList<Tile> tiles);

    LinkedList<Tile> getTilesColor(int color);

    LinkedList<Tile> pick(int color);

    LinkedList<Tile> getTiles();

    boolean isEmpty();

    int getSize();


}
