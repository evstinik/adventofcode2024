package puzzle07;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle07/input0.txt");
        assertEquals(18, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle07/input1.txt");
        assertEquals(2545, actual);
    }

    @Test
    void solveForInput2() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle07/input2.txt");
        assertEquals(4, actual);
    }
}