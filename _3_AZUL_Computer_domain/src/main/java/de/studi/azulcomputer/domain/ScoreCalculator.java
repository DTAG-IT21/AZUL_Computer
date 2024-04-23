package de.studi.azulcomputer.domain;

public class ScoreCalculator {

    private ScoreCalculator() {
    }

    public static int moveEval(Tile[][] board, int row, Tile tile) {
        int column = Mosaic.getColumn(row, tile);
        int score = 0;
        score += horizEval(board, row, column);
        score += vertEval(board, row, column);

        // Add one point if no points were given yet (tile has no neighbors)
        if (score == 0) {
            score++;
        }

        score += diagEval(board, row, column);
        return score;
    }

    private static int horizEval(Tile[][] board, int row, int column) {
        int score = 0;

        // Check left of placed tile
        int left = column - 1;
        while (left >= 0 && board[row][left] != null) {
            score++;
            left--;
        }

        // Check right of placed tile
        int right = column + 1;
        while (right < 5 && board[row][right] != null) {
            score++;
            right++;
        }

        // Add one point if at least one point was added (for placed tile itself)
        if (score > 0) {
            score++;
            // Bonus points for full horizontal
            if (score == 5) {
                score = 10;
            }
        }

        return score;
    }

    private static int vertEval(Tile[][] board, int row, int column) {
        int score = 0;

        // Check above placed tile
        int up = row - 1;
        while (up >= 0 && board[up][column] != null) {
            score++;
            up--;
        }

        // Check below placed tile
        int down = row + 1;
        while (down < 5 && board[down][column] != null) {
            score++;
            down++;
        }

        // Add one point if at least one point was added (for placed tile itself)
        if (score > 0) {
            score++;
            // Bonus points for full vertical
            if (score == 5) {
                score = 12;
            }
        }
        return score;
    }

    private static int diagEval(Tile[][] board, int row, int column) {

        // Check if all tiles in one diagonal are placed for diagonal bonus points
        int j = row + 1;
        int k = column + 1;
        for (int i = 0; i < 4; i++) {
            if (j > 4) {
                j = 0;
            }
            if (k > 4) {
                k = 0;
            }
            if (board[j][k] == null) {
                return 0;
            }
            j++;
            k++;

        }
        return 10;
    }
}
