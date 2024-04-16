package test.de.studi.azulcomputer;

import de.studi.azulcomputer.backend.ScoreCalculator;
import de.studi.azulcomputer.backend.Tile;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {
    @Test
    public void horizEval_single_Tile() {
        int actual_points = 1;
        Tile[][] mosaic = new Tile[5][5];
        int test_points = ScoreCalculator.moveEval(mosaic, 0, new Tile(0));
        assert (actual_points == test_points);
    }

    @Test
    public void horizEval_multiple_Tiles() {
        int actual_points = 4;
        Tile[][] mosaic = new Tile[5][5];
        mosaic[0][1] = new Tile(1);
        mosaic[0][2] = new Tile(2);
        mosaic[0][3] = new Tile(3);
        int test_points = ScoreCalculator.moveEval(mosaic, 0, new Tile(0));
        assert (actual_points == test_points);
    }
}
