package puzzle04;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Main().readInputAndSolve("puzzle03/input0.txt");
        assertEquals(4, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Main().readInputAndSolve("puzzle03/input1.txt");
        assertEquals(400, actual);
    }

    @Test
    void solveForInput2() throws URISyntaxException, IOException {
        var actual = new Main().readInputAndSolve("puzzle03/input2.txt");
        assertEquals(2, actual);
    }

    @Test
    void solveEdgeCase1() {
        var list = Arrays.asList(59, 62, 61, 62, 64);
        var result = new Main().areLevelsSafeQuick(list);
        assertTrue(result);
    }

    @Test
    void solveEdgeCase2() {
        var list = Arrays.asList(80, 79, 78, 80, 77);
        var result = new Main().areLevelsSafeQuick(list);
        assertTrue(result);
    }
}