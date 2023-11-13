package de.dhbw.dtag_it21.azul;

public class Manufacture {
    static final int MAX_TILES = 4;
    private Tile[] tiles = new Tile[MAX_TILES];

    public Manufacture() {
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) throws RuntimeException {
        if (tiles.length != 4){
            throw new RuntimeException("Too many or too few tiles");
        }
        this.tiles = tiles;
    }

    public void empty(){
        //Better than null?
        tiles = new Tile[MAX_TILES];
    }
}
