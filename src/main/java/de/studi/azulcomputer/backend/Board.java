package de.studi.azulcomputer.backend;

import java.util.Arrays;

public class Board{
    public int score;
    private int[][] board = new int[][]{
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
    };

    public Board(){
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void placeTile(int row, int column) throws IllegalMoveException{
            if (board[row][column] == 0){
                board[row][column] = 1;
                this.setScore(this.getScore() + ScoreCalculator.moveEval(board, row, column));
            }else{
                throw new IllegalMoveException();
            }
    }

    public void reset(){
        this.setScore(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int potentialScore(int row, int column){

        // Check if field is already set
        if (board[row][column] == 1){
            return 0;
        }

        // Set field, evaluate score and unset field
        board[row][column] = 1;
        score = ScoreCalculator.moveEval(board, row, column);
        board[row][column] = 0;

        return score;
    }
}


