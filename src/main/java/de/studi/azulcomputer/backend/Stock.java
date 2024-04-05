package de.studi.azulcomputer.backend;
import java.util.LinkedList;

public class Stock {
    private StockRow[] stock = new StockRow[5];
    private LinkedList<Tile> basement = new LinkedList<>();

    public Stock(){
        for (int i = 0; i < stock.length; i++) {
            stock[i] = new StockRow(i+1);
        }
    }

    // Stores given tiles in specified row of stock
    public void store(LinkedList<Tile> tiles, int row)throws IllegalMoveException{
        // Check for rule violations

        // No Tiles passed
        if(tiles.isEmpty()){
            return;
        }

        // Bad row index
        if(row < 0 || row >= stock.length) {
            throw new IllegalMoveException("Row out of bounds");
        }

        // Tiles have wrong color
        if(stock[row].getCurrentColor() != tiles.getFirst().getColor()){
            throw new IllegalMoveException("Current color doesn't match");
        }

        // Place tiles; place in basement if too many tiles are passed
        for(Tile tile : tiles){
            if (!stock[row].addTile(tile)){
                basement.add(tile);
            }
        }
    }

    // Delivers all full rows in stock
    public LinkedList<Integer> getFullRows(){
        LinkedList<Integer> fullRows = new LinkedList<>();

        // @TODO DRY
        for(StockRow row: stock){
            if(row.getCurrentCount() == row.getMaxTiles()){
                fullRows.add(row.getMaxTiles() - 1);
            }
        }

        return fullRows;
    }

    // Clears specified row of stock
    public void clearRow(int row) throws IllegalMoveException{
        // Check for bad row
        if(row < 0 || row >= stock.length){
            throw new IllegalMoveException("Row out of bounds");
        }

        // Clear row by setting it to new array
        stock[row].clear();
    }
}
