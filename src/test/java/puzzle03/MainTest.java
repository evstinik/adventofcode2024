package puzzle03;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Main().readInputAndSolve("puzzle03/input0.txt");
        assertEquals(2, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Main().readInputAndSolve("puzzle03/input1.txt");
        assertEquals(334, actual);
    }
}