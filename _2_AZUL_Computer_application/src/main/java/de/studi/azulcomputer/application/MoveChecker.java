package de.studi.azulcomputer.application;

import de.studi.azulcomputer.domain.Mosaic;
import de.studi.azulcomputer.domain.Stock;
import de.studi.azulcomputer.domain.StockRow;
import de.studi.azulcomputer.domain.Tile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MoveChecker {

    public static void isLegalStore(Stock stock, Tile[][] pattern, LinkedList<Integer> rows, int color) throws IllegalMoveException {
        LinkedList<Integer> checkedRows = getCheckedRows(rows);
        checkSpace(checkedRows, stock);
        checkColors(checkedRows, stock, color);
        checkMosaicPlaced(checkedRows, pattern, color);
    }

    private static Map<Integer, Integer> countRows(LinkedList<Integer> linkedList) {
        Map<Integer, Integer> occurrences = new HashMap<>();

        for (Integer number : linkedList) {
            occurrences.put(number, occurrences.getOrDefault(number, 0) + 1);
        }

        return occurrences;
    }

    private static LinkedList<Integer> getCheckedRows(LinkedList<Integer> rows) {
        LinkedList<Integer> checkedRows = new LinkedList<>();
        for (int row : rows) {
            if (row != -1) {
                checkedRows.add(row);
            }
        }
        return checkedRows;
    }

    private static void checkSpace(LinkedList<Integer> rows, Stock stock) throws IllegalMoveException {
        Map<Integer, Integer> rowCount = countRows(rows);
        for (Map.Entry<Integer, Integer> entry : rowCount.entrySet()) {
            StockRow row = stock.getRows()[entry.getKey()];
            int maxTiles = row.getMaxTiles();
            int currentCount = row.getCurrentCount();
            int freeSpace = maxTiles - currentCount;

            if (freeSpace < entry.getValue()) {
                throw new IllegalMoveException("Too many tiles are placed in one row.");
            }
        }
    }

    private static void checkColors(LinkedList<Integer> rows, Stock stock, int color) throws IllegalMoveException {
        for (Integer i : rows) {
            if (stock.getFirst(i) != null && stock.getFirst(i).getColor() != color) {
                throw new IllegalMoveException("Tiles of different colors are not allowed in the same stock row.");
            }
        }
    }

    private static void checkMosaicPlaced(LinkedList<Integer> rows, Tile[][] pattern, int color) throws IllegalMoveException {
        // Check mosaic issues
        for (int row : rows) {
            int column = Mosaic.getColumn(row, color);
            if (pattern[row][column] != null) {
                throw new IllegalMoveException("Tile of selected color is already placed in selected mosaic row.");
            }
        }
    }
}
