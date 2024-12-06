package puzzleXX;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle11Test {
    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Puzzle11().solve("puzzleXX/puzzle11_input0.txt");
        assertEquals(41, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Puzzle11().solve("puzzleXX/puzzle11_input1.txt");
        assertEquals(5242, actual);
    }
}
