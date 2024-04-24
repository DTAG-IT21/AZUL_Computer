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

        // Only check rows without basement because store rules do not apply to basement
        LinkedList<Integer> checkedRows = new LinkedList<>();
        for (int row : rows){
            if (row != -1){
                checkedRows.add(row);
            }
        }

        // Check space issues
        Map<Integer, Integer> rowCount  = countRows(checkedRows);
        for (Map.Entry<Integer, Integer> entry : rowCount.entrySet()) {
            StockRow row = stock.getRows()[entry.getKey()];
            int maxTiles = row.getMaxTiles();
            int currentCount = row.getCurrentCount();
            int freeSpace = maxTiles - currentCount;

            if (freeSpace < entry.getValue()){
                throw new IllegalMoveException("Too many tiles are placed in one row.");
            }

        }

        // Check color mismatch
        for(Integer i : checkedRows){
            if (stock.getFirst(i) != null && stock.getFirst(i).getColor() != color){
                throw new IllegalMoveException("Tiles of different colors are not allowed in the same stock row.");
            }
        }

        // Check mosaic issues
        for (int row : checkedRows){
            int column = Mosaic.getColumn(row, color);
            if (pattern[row][column] != null){
                throw new IllegalMoveException("Tile of selected color is already placed in selected mosaic row.");
            }
        }
    }

    private static Map<Integer, Integer> countRows(LinkedList<Integer> linkedList) {
        Map<Integer, Integer> occurrences = new HashMap<>();

        for (Integer number : linkedList) {
            occurrences.put(number, occurrences.getOrDefault(number, 0) + 1);
        }

        return occurrences;
    }
}
