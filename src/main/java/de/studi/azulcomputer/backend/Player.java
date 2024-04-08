package de.studi.azulcomputer.backend;

public class Player {

    private Mosaic mosaic;
    private Stock stock;

    public Player() {
        mosaic = new Mosaic();
        stock = new Stock();
    }

    public Mosaic getMosaic() {
        return mosaic;
    }

    public Stock getStock() {
        return stock;
    }


}
