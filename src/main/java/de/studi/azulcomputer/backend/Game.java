package de.studi.azulcomputer.backend;

import java.util.LinkedList;

public class Game {
    private final Player[] players;
    private final TileStore[] manufactures;
    private int currentPlayer;

    public Game() {
        currentPlayer = 0;
        players = new Player[2];
        Middle middle = new Middle();
        middle.addGameStone();
        manufactures = new TileStore[]{
                middle,
                new Manufacture(middle),
                new Manufacture(middle),
                new Manufacture(middle),
                new Manufacture(middle),
                new Manufacture(middle),
        };
    }

    public void pick(int manufactureIndex, int color, LinkedList<Integer> rows) throws IllegalMoveException {
        Player player = players[currentPlayer];

        // Check if middle is picked
        if (manufactureIndex == 0) {
            // Check if game stone is still in middle
            LinkedList<Tile> gameStone = manufactures[manufactureIndex].pick(Tile.colors.get("gameStone"));
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

        currentPlayer = ~currentPlayer;
    }

    public void place() throws IllegalMoveException {
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            if (player.hasGameStone()) {
                if (currentPlayer != i) {
                    currentPlayer = ~currentPlayer;
                }
            }
            player.placeFull();
        }
    }
}
