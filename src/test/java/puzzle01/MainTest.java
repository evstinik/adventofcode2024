package puzzle01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void shouldReturnCorrectResultForInput0() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle01/input0.txt");
        assertEquals(11, actual);
    }

    @Test
    void shouldReturnCorrectResultForInput1() throws URISyntaxException, IOException {
        var actual = new Main().solve("puzzle01/input1.txt");
        assertEquals(1320851, actual);
    }
}