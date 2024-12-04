package puzzle08;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle07/input0.txt");
        assertEquals(9, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle07/input1.txt");
        assertEquals(1886, actual);
    }
}