package puzzleXX;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle09Test {
    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Puzzle09().solve("puzzleXX/puzzle09_input0.txt");
        assertEquals(143, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Puzzle09().solve("puzzleXX/puzzle09_input1.txt");
        assertEquals(6498, actual);
    }
}
