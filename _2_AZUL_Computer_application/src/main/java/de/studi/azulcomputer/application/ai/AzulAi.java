package de.studi.azulcomputer.application.ai;

import de.studi.azulcomputer.application.Game;
import de.studi.azulcomputer.application.IllegalMoveException;
import de.studi.azulcomputer.application.Move;
import de.studi.azulcomputer.application.util.MoveChecker;
import de.studi.azulcomputer.application.Player;
import de.studi.azulcomputer.domain.Stock;
import de.studi.azulcomputer.domain.Tile;
import de.studi.azulcomputer.domain.TileStore;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Math.pow;


public class AzulAi {

    public static final int SIMULATION_DEPTH = 4;
    public static int[][] POSSIBLE_MOVES;

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
                    if (POSSIBLE_MOVES == null){
                        POSSIBLE_MOVES = generateNumbers(6);
                    }
                    for (int move : POSSIBLE_MOVES[tiles.size() - 1]){
                        LinkedList<Integer> rows = new LinkedList<>();
                        int length = String.valueOf(move).length();
                        for (int i = length - 1; i >= 0; i--) {
                            int digit = (int) (move / pow(10, i)) % 10;
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

    public static int[][] generateNumbers(int base){
        int[][] numbers = new int[4][];
        numbers[0] = generateCombinations(1, 5);
        numbers[1] = generateCombinations(2, 5);
        numbers[2] = generateCombinations(3, 5);
        numbers[3] = generateCombinations(4, 5);
        return numbers;
    }

    public static int[] generateCombinations(int n, int m) {
        // Überprüfen, ob die Eingaben gültig sind
        if (n <= 0 || m < 0) {
            System.out.println("Ungültige Eingaben.");
            return new int[0]; // Leeres Array zurückgeben, um einen Fehler anzuzeigen
        }

        List<Integer> combinationList = new ArrayList<>();
        generateCombinationsHelper(n, 0, new int[n], m, combinationList);

        // Konvertierung der Liste in ein Integer-Array
        int[] combinations = new int[combinationList.size()];
        for (int i = 0; i < combinationList.size(); i++) {
            combinations[i] = combinationList.get(i);
        }

        return combinations;
    }

    private static void generateCombinationsHelper(int n, int index, int[] currentCombination, int m, List<Integer> combinationList) {
        // Basisfall: Wenn die Kombination fertig ist, füge sie der Liste hinzu
        if (index == n) {
            int combination = 0;
            for (int digit : currentCombination) {
                combination = combination * 10 + digit;
            }
            combinationList.add(combination);
            return;
        }

        // Für jede Ziffer von 0 bis m prüfen, ob sie zur Kombination hinzugefügt werden kann
        for (int digit = 0; digit <= m; digit++) {
            currentCombination[index] = digit;
            generateCombinationsHelper(n, index + 1, currentCombination, m, combinationList);
        }
    }

    private static void makeMove(Move move, Game game) throws IllegalMoveException {
        game.pick(move.getManufacture(), move.getColor(), move.getRows());
    }

    public static void play(Game game) throws IllegalMoveException {
        int numThreads = Runtime.getRuntime().availableProcessors();
        Move move = getBestMove(game, SIMULATION_DEPTH, numThreads);
        makeMove(move, game);
    }

    public static int evaluatePosition(Player currentPlayer, Player opponent){
        return currentPlayer.getScore() - opponent.getScore();
    }

    public static Move getBestMove(final Game game, final int depth, final int numThreads) throws IllegalMoveException {
        LinkedList<Move> legalMoves = getLegalMoves(game);
        Move bestMove = null;
        int bestValue = Integer.MIN_VALUE;
        Player currentPlayer = game.getCurrentPlayer();
        Player opponent = game.getOpponent();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CompletionService<AbstractMap.SimpleEntry<Move, Integer>> completionService = new ExecutorCompletionService<>(executor);

        for (final Move move : legalMoves) {
            completionService.submit(new Callable<AbstractMap.SimpleEntry<Move, Integer>>() {
                @Override
                public AbstractMap.SimpleEntry<Move, Integer> call() throws Exception {
                    Game tempGame = new Game(game);
                    makeMove(move, tempGame);
                    int value = minValue(tempGame, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, currentPlayer, opponent);
                    return new AbstractMap.SimpleEntry<>(move, value);
                }
            });
        }

        try {
            for (int i = 0; i < legalMoves.size(); i++) {
                Future<AbstractMap.SimpleEntry<Move, Integer>> future = completionService.take();
                AbstractMap.SimpleEntry<Move, Integer> result = future.get();
                Move move = result.getKey();
                int value = result.getValue();
                if (value > bestValue) {
                    bestValue = value;
                    bestMove = move;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
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
