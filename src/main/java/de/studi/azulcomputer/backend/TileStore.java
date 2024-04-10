package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public interface TileStore {

    public void load(LinkedList<Tile> tiles);

    public LinkedList<Tile> pick(int color);

    public boolean isEmpty();
}
