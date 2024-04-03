package de.studi.azulcomputer.backend;

public class Board{
    public int score;
    private int[][] pattern = new int[5][5];

    public Board(){
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void placeTile(int row, int column) throws IllegalMoveException{
            if (this.pattern[row][column] == 0){
                this.pattern[row][column] = 1;
                this.setScore(this.getScore() + ScoreCalculator.moveEval(this.pattern, row, column));
            }else{
                throw new IllegalMoveException();
            }
    }

    public void reset(){
        this.setScore(0);
        for (int i = 0; i < this.pattern.length; i++) {
            for (int j = 0; j < this.pattern[i].length; j++) {
                this.pattern[i][j] = 0;
            }
        }
    }

    public int potentialScore(int row, int column){

        // Check if field is already set
        if (this.pattern[row][column] == 1){
            return 0;
        }

        // Set field, evaluate score and unset field
        this.pattern[row][column] = 1;
        int result = ScoreCalculator.moveEval(this.pattern, row, column);
        this.pattern[row][column] = 0;

        return result;
    }
}


