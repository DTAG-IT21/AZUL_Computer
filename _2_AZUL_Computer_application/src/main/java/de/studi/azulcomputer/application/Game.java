package de.studi.azulcomputer.application;

import de.studi.azulcomputer.application.util.MoveChecker;
import de.studi.azulcomputer.domain.Manufacture;
import de.studi.azulcomputer.domain.Middle;
import de.studi.azulcomputer.domain.Stock;
import de.studi.azulcomputer.domain.StockRow;
import de.studi.azulcomputer.domain.Tile;
import de.studi.azulcomputer.domain.TileBag;
import de.studi.azulcomputer.domain.TileStore;

import java.util.LinkedList;

public class Game {
    private final Player[] players;
    private final TileStore[] manufactures;
    private final TileBag tilebag;
    private final LinkedList<Listener> listeners = new LinkedList<>();
    private final LinkedList<Tile> discardedTiles = new LinkedList<>();
    private int currentPlayer;

    public Game() {
        tilebag = new TileBag();
        currentPlayer = 0;
        players = new Player[]{new Player(), new Player()};
        Middle middle = new Middle();

        manufactures = new TileStore[]{
                middle,
                new Manufacture(middle),
                new Manufacture(middle),
                new Manufacture(middle),
                new Manufacture(middle),
                new Manufacture(middle),
        };

        fillManufactures();
        message();
    }

    public Game(Game game){
        tilebag = new TileBag(game.tilebag);
        currentPlayer = game.currentPlayer;
        players = new Player[]{new Player(game.players[0]), new Player(game.players[1])};
        manufactures = new TileStore[]{
                new Middle((Middle) game.manufactures[0]),
                new Manufacture((Manufacture) game.manufactures[1]),
                new Manufacture((Manufacture) game.manufactures[2]),
                new Manufacture((Manufacture) game.manufactures[3]),
                new Manufacture((Manufacture) game.manufactures[4]),
                new Manufacture((Manufacture) game.manufactures[5]),
        };

        for (Tile tile : game.discardedTiles){
            discardedTiles.add(new Tile(tile));
        }
    }

    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public Player getOpponent() {
        return players[(currentPlayer + 1) % players.length];
    }

    public TileStore[] getManufactures() {
        return manufactures;
    }

    public int[] getScore() {
        return new int[]{players[0].getScore(), players[1].getScore()};
    }

    public int[][] getMosaic(int playerIndex) {
        Player player = players[playerIndex];
        Tile[][] mosaic = player.getPattern();
        int[][] tiles = new int[mosaic.length][mosaic[0].length];
        for (int i = 0; i < mosaic.length; i++) {
            for (int j = 0; j < mosaic[0].length; j++) {
                if (mosaic[i][j] != null) {
                    tiles[i][j] = mosaic[i][j].getColor();
                } else {
                    tiles[i][j] = Tile.getIntColor("null");
                }
            }
        }
        return tiles;
    }

    public void pick(int manufactureIndex, int color, LinkedList<Integer> rows) throws IllegalMoveException {
        Player player = players[currentPlayer];
        LinkedList<Tile> tiles = manufactures[manufactureIndex].getTilesColor(color);

        if (tiles.size() != rows.size()) {
            throw new InternalError("Amount of tiles does not match amount of selected rows");
        }

        MoveChecker.isLegalStore(player.getStock(), player.getPattern(), rows, color);

        // Check if middle is picked
        if (manufactureIndex == 0) {
            // Check if game stone is still in middle
            LinkedList<Tile> gameStone = manufactures[manufactureIndex].pick(Tile.getIntColor("gameStone"));
            if (!gameStone.isEmpty()) {
                // Store game stone in basement
                player.store(gameStone.getFirst(), 5);
            }
        }

        tiles = manufactures[manufactureIndex].pick(color);
        for (int i = 0; i < tiles.size(); i++) {
            player.store(tiles.get(i), rows.get(i));
        }

        if (allTilesPicked()) {
            placingPhase();
        } else {
            changePlayer();
        }
        message();
    }

    public boolean allTilesPicked() {
        boolean allTilesPicked = true;
        for (TileStore t : manufactures) {
            if (!t.isEmpty())
                return false;
        }
        return allTilesPicked;
    }

    public void placingPhase() {
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.hasGameStone()) {
                if (currentPlayer != i) {
                    changePlayer();
                }
            }
            tilebag.discard(player.placeFull());
        }
        fillManufactures();
    }

    public void fillManufactures() {
        for (TileStore man : manufactures) {
            if (man instanceof Middle mid) {
                mid.addGameStone();
            } else {
                man.load(tilebag.draw(4));
            }
        }
    }

    public LinkedList<Integer> mosaicColors() {
        LinkedList<Integer> colors = new LinkedList<>();
        for (Player player : players) {
            Tile[][] mosaic = player.getPattern();
            for (Tile[] row : mosaic) {
                for (Tile tile : row) {
                    if (tile != null) {
                        colors.add(tile.getColor());
                    } else {
                        colors.add(Tile.getIntColor("null"));
                    }
                }
            }
        }
        return colors;
    }

    public LinkedList<Integer> stockColors() {
        LinkedList<Integer> colors = new LinkedList<>();
        for (Player player : players) {
            Stock stock = player.getStock();
            for (StockRow row : stock.getRows()) {
                int currentCount = row.getCurrentCount();
                for (int j = 0; j < currentCount; j++) {
                    colors.add(row.getFirst().getColor());
                }
                int nulls = row.getMaxTiles() - row.getCurrentCount();
                for (int j = 0; j < nulls; j++) {
                    colors.add(Tile.getIntColor("null"));
                }
            }
        }
        return colors;
    }

    public LinkedList<Integer> floorColors() {
        LinkedList<Integer> colors = new LinkedList<>();
        for (Player player : players) {
            LinkedList<Tile> basement = player.getStock().getBasement();
            LinkedList<Integer> playercolors = new LinkedList<>();
            for (Tile tile : basement) {
                if (playercolors.size() < 7){
                    playercolors.add(tile.getColor());
                }
            }

            int nulls = 7 - playercolors.size();
            for (int j = 0; j < nulls; j++) {
                colors.add(Tile.getIntColor("null"));
            }
            colors.addAll(playercolors);
        }
        return colors;
    }

    public LinkedList<Integer> manufactureColors(int manufactureIndex) {
        LinkedList<Integer> colors = new LinkedList<>();
        TileStore manufacture = manufactures[manufactureIndex];
        for (Tile tile : manufacture.getTiles()) {
            colors.add(tile.getColor());
        }

        int nulls = manufacture.getSize() - colors.size();
        for (int i = 0; i < nulls; i++) {
            colors.add(Tile.getIntColor("null"));
        }

        return colors;
    }

    public void subscribe(Listener listener) {
        listeners.add(listener);
        message();
    }

    public void unsubscribe(Listener listener) {
        listeners.remove(listener);
    }

    public void message() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    public void changePlayer() {
        currentPlayer ^= 1;
    }

    public boolean isGameOver(){
        for (Player player : players) {
            for (Tile[] row : player.getPattern()){
                int counter = 0;
                for (Tile tile : row) {
                    if (tile != null) {
                        counter++;
                    }else{
                        break;
                    }
                    if (counter == 5){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
