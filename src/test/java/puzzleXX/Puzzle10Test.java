package puzzleXX;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle10Test {
    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Puzzle10().solve("puzzleXX/puzzle09_input0.txt");
        assertEquals(123, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Puzzle10().solve("puzzleXX/puzzle09_input1.txt");
        assertEquals(5017, actual);
    }
}
