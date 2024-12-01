package puzzle02;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle01/input0.txt");
        assertEquals(31, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle01/input1.txt");
        assertEquals(26859182L, actual);
    }
}