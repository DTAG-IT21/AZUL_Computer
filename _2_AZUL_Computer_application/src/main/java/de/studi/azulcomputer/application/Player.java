package de.studi.azulcomputer.application;

import de.studi.azulcomputer.domain.IllegalMoveException;
import de.studi.azulcomputer.domain.Mosaic;
import de.studi.azulcomputer.domain.Stock;
import de.studi.azulcomputer.domain.Tile;

public class Player {

    private final Mosaic mosaic;
    private final Stock stock;
    private int score;

    public Player() {
        score = 0;
        mosaic = new Mosaic();
        stock = new Stock();
    }

    public int getScore() {
        return score;
    }

    public void placeTile(int row, Tile tile) throws IllegalMoveException {
        mosaic.placeTile(row, tile);
        this.score += ScoreCalculator.moveEval(mosaic.getPattern(), row, tile);
    }

    public void placeFull() throws IllegalMoveException {
        for (int row : stock.getFullRows()) {
            placeTile(row, stock.getFirst(row));
            stock.clearRow(row);
        }

        clearBasement();
    }

    public void store(Tile tile, int row) throws IllegalMoveException {
        stock.store(tile, row);
    }

    public void reset() {
        mosaic.reset();
        stock.reset();
        score = 0;
    }

    public void clearBasement() {
        int counter = 0;
        int penalty;
        int negative_score = 0;

        for (Tile tile : stock.getBasement()) {
            switch (counter) {
                case 0, 1:
                    counter++;
                    penalty = 1;
                    break;
                case 2, 3, 4:
                    counter++;
                    penalty = 2;
                    break;
                default:
                    penalty = 3;
                    break;
            }
            negative_score += penalty;
        }
        score -= negative_score;
        clearRow(-1);
    }

    public boolean hasGameStone() {
        for (Tile tile : stock.getBasement()) {
            if (tile.getColor() == Tile.colorToInt.get("gameStone")) {
                return true;
            }
        }
        return false;
    }

    public void clearRow(int row) {
        stock.clearRow(row);
    }

    public int getRowColor(int row) {
        return stock.getFirst(row).getColor();
    }

    public int potentialScore(int row, Tile tile) {
        return ScoreCalculator.moveEval(mosaic.getPattern(), row, tile);
    }

    public Tile[][] getMosaic(){
        return mosaic.getPattern();
    }

    public Stock getStock() {
        return stock;
    }
}
