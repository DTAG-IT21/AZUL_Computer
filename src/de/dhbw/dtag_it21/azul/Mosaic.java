package de.dhbw.dtag_it21.azul;

public class Mosaic {
    static final int SIZE = 5;
    private final Tile[][] tiles = new Tile[SIZE][SIZE];
    static final Tile[][] pattern;

    static {
        pattern = new Tile[][]{
                {
                        new Tile("blue"),
                        new Tile("yellow"),
                        new Tile("red"),
                        new Tile("black"),
                        new Tile("white")
                },
                {
                        new Tile("white"),
                        new Tile("blue"),
                        new Tile("yellow"),
                        new Tile("red"),
                        new Tile("black")
                },
                {
                        new Tile("black"),
                        new Tile("white"),
                        new Tile("blue"),
                        new Tile("yellow"),
                        new Tile("red")
                },
                {
                        new Tile("red"),
                        new Tile("black"),
                        new Tile("white"),
                        new Tile("blue"),
                        new Tile("yellow")
                },
                {
                        new Tile("yellow"),
                        new Tile("red"),
                        new Tile("black"),
                        new Tile("white"),
                        new Tile("blue")
                }
        };
    }

    public Mosaic() {
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void addTile(Tile tile, int row){
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            if (pattern[row][index].getColor().equals(tile.getColor())){
                tiles[row][index] = tile;
                return;
            }
        }
        throw new RuntimeException("Fatal error: Could not find tile color in pattern");
    }
}
