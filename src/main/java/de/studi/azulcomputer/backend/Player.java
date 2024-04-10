package de.studi.azulcomputer.backend;

public class Player {

    private Mosaic mosaic;
    private Stock stock;
    private int score;

    public Player() {
        score = 0;
        mosaic = new Mosaic();
        stock = new Stock();
    }

    public int getScore() {
        return score;
    }

    public void placeTile(Tile tile, int row) throws IllegalMoveException{
        mosaic.placeTile(row, tile);
        this.score += ScoreCalculator.moveEval(mosaic.getPattern(), row, tile);
    }

    public void store(Tile tile, int row) throws IllegalMoveException{
        stock.store(tile, row);
    }

    public void reset(){
        mosaic.reset();
        stock.reset();
        score = 0;
    }

    public void clearRow(int row){
        stock.clearRow(row);
    }

    public int getRowColor(int row){
        return stock.getRowColor(row);
    }
}
