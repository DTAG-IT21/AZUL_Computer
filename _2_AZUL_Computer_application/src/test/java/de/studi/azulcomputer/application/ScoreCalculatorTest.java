package de.studi.azulcomputer.application;

import de.studi.azulcomputer.domain.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculatorTest {
    @Test
    public void eval_single_Tile() {
        int actual_points = 1;
        Tile[][] mosaic = new Tile[5][5];

        int test_points = ScoreCalculator.moveEval(mosaic, 0, new Tile(0));
        assertEquals (actual_points, test_points);
    }

    @Test
    public void horizEval_multiple_Tiles() {
        int actual_points = 4;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[0][1] = new Tile(1);
        mosaic[0][3] = new Tile(3);

        int test_points = ScoreCalculator.moveEval(mosaic, 0, new Tile(2));
        assertEquals(actual_points, test_points);
    }

    @Test
    public void horizEval_multiple_Tiles_with_spaces() {
        int actual_points = 3;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[0][1] = new Tile(1);
        mosaic[0][4] = new Tile(4);

        int test_points = ScoreCalculator.moveEval(mosaic, 0, new Tile(2));
        assertEquals (actual_points, test_points);
    }

    @Test
    public void horizEval_bonus_points(){
        int actual_points = 10;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[0][1] = new Tile(1);
        mosaic[0][2] = new Tile(2);
        mosaic[0][4] = new Tile(4);

        int test_points = ScoreCalculator.moveEval(mosaic, 0, new Tile(3));
        assertEquals (actual_points, test_points);
    }

    @Test
    public void verticalEval_multiple_Tiles() {
        int actual_points = 3;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[2][0] = new Tile(2);

        int test_points = ScoreCalculator.moveEval(mosaic, 1, new Tile(4));
        assertEquals (actual_points, test_points);
    }

    @Test
    public void verticalEval_multiple_Tiles_with_spaces() {
        int actual_points = 3;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[2][0] = new Tile(1);
        mosaic[4][0] = new Tile(4);

        int test_points = ScoreCalculator.moveEval(mosaic, 1, new Tile(4));
        assertEquals (actual_points, test_points);
    }

    @Test
    public void verticalEval_bonus_points(){
        int actual_points = 12;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[1][0] = new Tile(4);
        mosaic[2][0] = new Tile(3);
        mosaic[3][0] = new Tile(2);

        int test_points = ScoreCalculator.moveEval(mosaic, 4, new Tile(1));
        assertEquals (actual_points, test_points);
    }

    @Test
    public void diagonalEval_bonus_points(){
        int actual_points = 11;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][0] = new Tile(0);
        mosaic[1][1] = new Tile(0);
        mosaic[2][2] = new Tile(0);
        mosaic[3][3] = new Tile(0);

        int test_points = ScoreCalculator.moveEval(mosaic, 4, new Tile(0));
        assertEquals (actual_points, test_points);
    }
}