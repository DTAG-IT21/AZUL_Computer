package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class Manufacture implements TileStore{
    LinkedList<Tile> tiles = new LinkedList<>();
    private final Middle middle;

    public Manufacture(Middle middle) {
        this.middle = middle;
    }

    public void load(LinkedList<Tile> tiles) {
        // @TODO Magic Number
        if (tiles.size() != 4) {
            System.out.println("Error: Tile array length is incorrect");
        }else{
            this.tiles = tiles;
        }
    }

    public LinkedList<Tile> pick(int color){
        LinkedList<Tile> picked = new LinkedList<>();
        LinkedList<Tile> discard = new LinkedList<>();

        for (Tile tile: tiles){
            if (tile.getColor() == color){
                picked.add(tile);
                tiles.remove(tile);
            }else{
                discard.add(tile);
                tiles.remove(tile);
            }
        }

        middle.load(discard);
        return picked;
    }

    public boolean isEmpty(){
        return tiles.isEmpty();
    }
}
