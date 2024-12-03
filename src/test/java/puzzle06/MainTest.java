package puzzle06;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void solveForInput0() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle06/input0.txt");
        assertEquals(48, actual);
    }

    @Test
    void solveForInput1() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle06/input1.txt");
        assertEquals(84893551L, actual);
    }
}