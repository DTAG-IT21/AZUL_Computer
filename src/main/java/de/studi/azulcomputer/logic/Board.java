package de.studi.azulcomputer.logic;



import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends GridPane {
    private boolean[][] board = new boolean[5][5];

    public Board() {
        super();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Rectangle rectangle = createCell();
                int finalRow = row;
                int finalCol = col;
                rectangle.setOnMouseClicked(event -> toggleStone(finalRow, finalCol, rectangle));
                add(rectangle, col, row);
            }
        }
    }

    private Rectangle createCell() {
        Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);
        return rectangle;
    }

    private void toggleStone(int row, int col, Rectangle rectangle) {
        if (board[row][col]) {
            board[row][col] = false;
            rectangle.setFill(Color.TRANSPARENT);
        } else {
            board[row][col] = true;
            rectangle.setFill(Color.BLUE); // Beispiel: Blauer Stein
        }
    }


}
