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

    private void placeTile(Tile tile, int row) throws IllegalMoveException{
        mosaic.placeTile(row, tile);
        this.score += ScoreCalculator.moveEval(mosaic.getPattern(), row, tile);
    }

    public void placeFull() throws IllegalMoveException {
        for (int row : stock.getFullRows()){
            placeTile(stock.getFirst(row), row);
            stock.clearRow(row);
        }

        clearBasement();
    }

    public void store(Tile tile, int row) throws IllegalMoveException{
        stock.store(tile, row);
    }

    public void reset(){
        mosaic.reset();
        stock.reset();
        score = 0;
    }

    public void clearBasement(){
        int counter = 0;
        int penalty = 0;
        int negative_score = 0;

        for(Tile tile: stock.getBasement()){
            switch(counter){
                case 0, 1: counter++; penalty = 1; break;
                case 2, 3, 4: counter++; penalty = 2; break;
                default: penalty = 3; break;
            }
            negative_score += penalty;
        }
        score -= negative_score;
        clearRow(-1);
    }

    public boolean hasGameStone(){
        for(Tile tile: stock.getBasement()){
            if(tile.getColor() == Tile.colors.get("gameStone")) {
                return true;
            }
        }
        return false;
    }

    public void clearRow(int row){
        stock.clearRow(row);
    }

    public int getRowColor(int row){
        return stock.getRowColor(row);
    }
}
