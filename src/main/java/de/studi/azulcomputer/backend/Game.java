package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class Game {
    private final Player[] players;
    private final TileStore[] manufactures;
    private int currentPlayer;
    private final TileBag tilebag;
    private final LinkedList<Listener> listeners = new LinkedList<>();

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

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int[][] getMosaic(int playerIndex) {
        Player player = players[playerIndex];
        Tile[][] mosaic = player.getMosaic();
        int [][] tiles = new int[mosaic.length][mosaic[0].length];
        for (int i = 0; i < mosaic.length; i++) {
            for (int j = 0; j < mosaic[0].length; j++) {
                if (mosaic[i][j] != null) {
                    tiles[i][j] = mosaic[i][j].getColor();
                }else {
                    tiles[i][j] = Tile.colorToInt.get("null");
                }
            }
        }
        return tiles;
    }

    public void pick(int manufactureIndex, int color, LinkedList<Integer> rows) throws IllegalMoveException {
        Player player = players[currentPlayer];

        // Check if middle is picked
        if (manufactureIndex == 0) {
            // Check if game stone is still in middle
            LinkedList<Tile> gameStone = manufactures[manufactureIndex].pick(Tile.colorToInt.get("gameStone"));
            if (!gameStone.isEmpty()) {
                // Store game stone in basement
                player.store(gameStone.getFirst(), -1);
            }
        }

        LinkedList<Tile> tiles = manufactures[manufactureIndex].pick(color);
        if (tiles.size() != rows.size()) {
            throw new InternalError("Tiles and rows do not match");
        }

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

    public void placingPhase() throws IllegalMoveException {
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.hasGameStone()) {
                if (currentPlayer != i) {
                    changePlayer();
                }
            }
            player.placeFull();
        }
        fillManufactures();
        message();
    }

    public void fillManufactures(){
        for (TileStore man : manufactures){
            if (man instanceof Middle mid){
                mid.addGameStone();
            }else{
                man.load(tilebag.draw(4));
            }
        }
        message();
    }

    public LinkedList<Integer> mosaicColors() {
        LinkedList<Integer> colors = new LinkedList<>();
        for (Player player : players) {
            Tile[][] mosaic = player.getMosaic();
            for (Tile[] row : mosaic) {
                for (Tile tile : row) {
                    if (tile != null) {
                        colors.add(tile.getColor());
                    } else {
                        colors.add(Tile.colorToInt.get("null"));
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
                    colors.add(Tile.colorToInt.get("null"));
                }
            }
        }
        return colors;
    }

    public LinkedList<Integer> floorColors() {
        LinkedList<Integer> colors = new LinkedList<>();
        for (Player player : players) {
            LinkedList<Tile> basement = player.getStock().getBasement();
            for (Tile tile : basement) {
                colors.add(tile.getColor());
            }

            int nulls = 7 - basement.size();
            for (int j = 0; j < nulls; j++) {
                colors.add(Tile.colorToInt.get("null"));
            }
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
            colors.add(Tile.colorToInt.get("null"));
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

    public void changePlayer(){
        currentPlayer ^= 1;
    }
}
