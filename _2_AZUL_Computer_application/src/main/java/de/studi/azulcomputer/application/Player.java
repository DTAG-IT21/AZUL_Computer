package de.studi.azulcomputer.application;

import de.studi.azulcomputer.application.util.ScoreCalculator;
import de.studi.azulcomputer.domain.Mosaic;
import de.studi.azulcomputer.domain.Stock;
import de.studi.azulcomputer.domain.Tile;

import java.util.LinkedList;

public class Player {

    private final Mosaic mosaic;
    private final Stock stock;
    private int score;

    public Player() {
        score = 0;
        mosaic = new Mosaic();
        stock = new Stock();
    }

    public Player(Player player) {
        score = player.getScore();
        mosaic = new Mosaic(player.mosaic);
        stock = new Stock(player.stock);
    }

    public int getScore() {
        return score;
    }

    public void placeTile(int row, Tile tile) {
        this.score += ScoreCalculator.moveEval(mosaic.getPattern(), row, tile.getColor());
        mosaic.placeTile(row, tile);
    }

    public LinkedList<Tile> placeFull() {
        LinkedList<Tile> discard = new LinkedList<>();
        for (int row : stock.getFullRows()) {
            placeTile(row, stock.getFirst(row));
            for (int i = 0; i < row; i++){
                discard.add(new Tile(stock.getFirst(row)));
            }
            stock.clearRow(row);
        }
        for (Tile tile : getStock().getBasement()){
            if (tile.getColor() != Tile.getIntColor("gameStone")){
                discard.add(tile);
            }
        }
        clearBasement();
        return discard;
    }

    public void store(Tile tile, int row) {
        stock.store(tile, row);
    }

    public void reset() {
        mosaic.reset();
        stock.reset();
        score = 0;
    }

    public void clearBasement() {
        score -= ScoreCalculator.getPenalty(stock.getBasement());
        clearRow(-1);
    }

    public boolean hasGameStone() {
        for (Tile tile : stock.getBasement()) {
            if (tile.getColor() == Tile.getIntColor("gameStone")) {
                return true;
            }
        }
        return false;
    }

    public void clearRow(int row) {
        stock.clearRow(row);
    }

    public int potentialScore(int row, int color) {
        return ScoreCalculator.moveEval(mosaic.getPattern(), row, color);
    }

    public Tile[][] getPattern() {
        return mosaic.getPattern();
    }

    public Stock getStock() {
        return stock;
    }
}
