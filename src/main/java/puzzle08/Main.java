package puzzle08;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        var main = new Main();
        if (args.length == 0) {
            System.out.println("Please provide an input file");
            return;
        }
        var answer = main.solve(args[1]);
        System.out.printf("Answer: %d", answer);
    }

    public int solve(String inputFile) throws URISyntaxException, IOException {
        var input = readInput(inputFile);
        return solvePuzzle(input);
    }

    private char[][] readInput(String inputFile) throws URISyntaxException, IOException, IllegalArgumentException {
        var resource = Main.class.getClassLoader().getResource(inputFile);
        if (resource == null) {
            throw new IllegalArgumentException("Input file " + inputFile + " not found");
        }
        var path = Paths.get(resource.toURI());
        var lines = Files.readAllLines(path);
        return lines.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private int solvePuzzle(char[][] input) {
        var w = input[0].length;
        var h = input.length;
        var result = 0;
        for (var y = 1; y < h - 1; y += 1) {
            for (var x = 1; x < w - 1; x += 1) {
                if (checkXmas(input, x, y)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private boolean checkXmas(char[][] input, int x, int y) {
        if (input[y][x] != 'A') {
            return false;
        }
        /*
        a..b
        c..d
         */
        var a = input[y-1][x-1];
        var b = input[y-1][x+1];
        var c = input[y+1][x-1];
        var d = input[y+1][x+1];
        var isDiagonal1Fine = a == 'M' && d == 'S' || a == 'S' && d == 'M';
        var isDiagonal2Fine = b == 'M' && c == 'S' || b == 'S' && c == 'M';
        return isDiagonal1Fine && isDiagonal2Fine;
    }
}
