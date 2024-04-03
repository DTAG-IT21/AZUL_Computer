package de.studi.azulcomputer.backend;

public class Board{
    public int score;
    private Tile[][] pattern = new Tile[5][5];
    public static int[][] colorPattern = new int[][]{
        {0, 1, 2, 3, 4},
        {1, 2, 3, 4, 0},
        {2, 3, 4, 0, 1},
        {3, 4, 0, 1, 2},
        {4, 0, 1, 2, 3}
    };

    public Board(){
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void placeTile(int row, int column, Tile tile) throws IllegalMoveException{
            if (this.pattern[row][column] == null && tile.getColor() == colorPattern[row][column]){
                this.pattern[row][column] = tile;
                this.setScore(this.getScore() + ScoreCalculator.moveEval(this.pattern, row, column));
            }else{
                throw new IllegalMoveException();
            }
    }

    public void reset(){
        this.setScore(0);
        for (int i = 0; i < this.pattern.length; i++) {
            for (int j = 0; j < this.pattern[i].length; j++) {
                this.pattern[i][j] = null;
            }
        }
    }

    public int potentialScore(int row, int column, Tile tile){

        // Check if field is already set
        if (this.pattern[row][column] != null){
            return 0;
        }

        // Set field, evaluate score and unset field
        this.pattern[row][column] = tile;
        int result = ScoreCalculator.moveEval(this.pattern, row, column);
        this.pattern[row][column] = null;

        return result;
    }
}


