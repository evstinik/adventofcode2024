package puzzle01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void shouldReturnCorrectHelloWorld() {
        var actual = Main.getHelloWorld();
        assertEquals("Hello world!", actual);
    }
}