package de.studi.azulcomputer.application.ai;

import de.studi.azulcomputer.application.Game;
import de.studi.azulcomputer.application.IllegalMoveException;
import de.studi.azulcomputer.application.Move;
import de.studi.azulcomputer.application.util.MoveChecker;
import de.studi.azulcomputer.application.Player;
import de.studi.azulcomputer.domain.Stock;
import de.studi.azulcomputer.domain.Tile;
import de.studi.azulcomputer.domain.TileStore;

import java.util.LinkedList;


public class AzulAi {

    public static final int SIMULATION_DEPTH = 4;

    private static LinkedList<Move> getLegalMoves(Game game){
        LinkedList<Move> legalMoves = new LinkedList<>();

        Player currentPlayer = game.getCurrentPlayer();
        Stock stock = currentPlayer.getStock();
        Tile[][] pattern = currentPlayer.getPattern();
        TileStore[] manufactures = game.getManufactures();

        for (int manufactureIndex = 0; manufactureIndex < manufactures.length; manufactureIndex++) {
            TileStore manufacture = manufactures[manufactureIndex];
            for (int color = 0; color < 5; color++){
                LinkedList<Tile> tiles = manufacture.getTilesColor(color);
                if (!tiles.isEmpty()){
                    LinkedList<Integer> possibleMoves = generateNumbers(tiles.size());
                    for (int move : possibleMoves){
                        LinkedList<Integer> rows = new LinkedList<>();
                        int length = String.valueOf(move).length();
                        for (int i = length - 1; i >= 0; i--) {
                            int digit = (int) (move / Math.pow(10, i)) % 10;
                            rows.add(digit);
                        }
                        while (rows.size() != tiles.size()){
                            rows.add(0);
                        }
                        try {
                            MoveChecker.isLegalStore(stock, pattern, rows, color);
                            legalMoves.add(new Move(manufactureIndex, color, rows));
                        }catch (IllegalMoveException ignored){}
                    }
                }
            }
        }
        return legalMoves;
    }

    private static LinkedList<Integer> generateNumbers(int n) {
        LinkedList<Integer> numbers = new LinkedList<>();
        generateNumbersHelper(n, 0, new int[n], numbers);
        return numbers;
    }

    private static void generateNumbersHelper(int n, int index, int[] currentNumber, LinkedList<Integer> numbers) {
        if (index == n) {
            int generatedNumber = 0;
            for (int i = 0; i < n; i++) {
                generatedNumber = generatedNumber * 10 + currentNumber[i];
            }
            numbers.add(generatedNumber);
            return;
        }
        for (int digit = 0; digit <= 5; digit++) {
            currentNumber[index] = digit;
            generateNumbersHelper(n, index + 1, currentNumber, numbers);
        }
    }

    private static void makeMove(Move move, Game game) throws IllegalMoveException {
        game.pick(move.getManufacture(), move.getColor(), move.getRows());
    }

    public static void play(Game game) throws IllegalMoveException {
        Move move = getBestMove(game, SIMULATION_DEPTH);
        makeMove(move, game);
    }

    public static int evaluatePosition(Player currentPlayer, Player opponent){
        return currentPlayer.getScore() - opponent.getScore();
    }

    public static Move getBestMove(Game game, int depth) throws IllegalMoveException {
        LinkedList<Move> legalMoves = getLegalMoves(game);

        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();

        for (Move move : legalMoves) {
            Game tempGame = new Game(game);
            makeMove(move, tempGame);
            int value = minValue(tempGame, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, opponent);

            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private static int maxValue(Game game, int depth, int alpha, int beta, Player currentPlayer, Player opponent) throws IllegalMoveException {
        if (depth == 0 || game.isGameOver()) {
            return evaluatePosition(currentPlayer, opponent);
        }

        LinkedList<Move> legalMoves = getLegalMoves(game);
        for (Move move : legalMoves) {
            Game tempGame = new Game(game);
            makeMove(move, tempGame);
            alpha = Math.max(alpha, minValue(tempGame, depth - 1, alpha, beta, currentPlayer, opponent));
            if (alpha >= beta) {
                return beta;
            }
        }
        return alpha;
    }

    private static int minValue(Game game, int depth, int alpha, int beta, Player currentPlayer, Player opponent) throws IllegalMoveException {
        if (depth == 0 || game.isGameOver()) {
            return evaluatePosition(currentPlayer, opponent);
        }

        LinkedList<Move> legalMoves = getLegalMoves(game);
        for (Move move : legalMoves) {
            Game tempGame = new Game(game);
            makeMove(move, tempGame);
            beta = Math.min(beta, maxValue(tempGame, depth - 1, alpha, beta, currentPlayer, opponent));
            if (beta <= alpha) {
                return alpha;
            }
        }
        return beta;
    }
}
