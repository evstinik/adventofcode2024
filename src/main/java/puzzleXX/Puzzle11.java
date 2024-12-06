package puzzleXX;

import puzzle08.Main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Puzzle11 {
    static class Vector2 {
        int x, y;
        Vector2(int x, int y) {
            this.x = x;
            this.y = y;
        }
        void byAdding(Vector2 a, Vector2 b) {
            x = a.x + b.x;
            y = a.y + b.y;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Vector2 v = (Vector2) obj;
            return x == v.x && y == v.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        public void copy(Vector2 v) {
            x = v.x;
            y = v.y;
        }
        public void rotateBy90() {
            var angle = Math.PI * 0.5;
            var _x = x * Math.cos(angle) - y * Math.sin(angle);
            var _y = x * Math.sin(angle) + y * Math.cos(angle);
            x = (int)_x;
            y = (int)_y;
        }
    }

    static class CellState {
        boolean visited = false;
        Set<Vector2> visitedDirections = new HashSet<>();
    }

    public static void main(String[] args) throws URISyntaxException, IOException, IllegalArgumentException {
        var main = new Puzzle11();
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

        // Initialize map state
        CellState[][] map = new CellState[h][w];
        Arrays.setAll(map, i -> {
            var row = new CellState[w];
            Arrays.setAll(row, j -> new CellState());
            return row;
        });

        // Find starting position
        var pos = new Vector2(0, 0);
        for (pos.y = 0; pos.y < h; pos.y++) {
            var found = false;
            for (pos.x = 0; pos.x < w; pos.x++) {
                if (input[pos.y][pos.x] == '^') {
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        var dir = new Vector2(0, -1);
        var counter = 0;
        var nextPos = new Vector2(0, 0);
        while (true) {
            if (isOutOfBounds(pos, input)) {
                break;
            }

            // Mark current position
            var cell = map[pos.y][pos.x];
            if (!cell.visited) {
                counter += 1;
            }
            cell.visited = true;
            if (cell.visitedDirections.contains(dir)) {
                // we already were here with the same direction => cycle => exit
                break;
            }
            cell.visitedDirections.add(dir);

            // Decide next position
            nextPos.byAdding(pos, dir);
            while (!isOutOfBounds(nextPos, input) && !isFree(nextPos, input)) {
                dir.rotateBy90();
                nextPos.byAdding(pos, dir);
            }

            // Move to next pos
            pos.copy(nextPos);
        }

        return counter;
    }

    private boolean isOutOfBounds(Vector2 p, char[][] input) {
        if (p.x < 0 || p.y < 0) return true;
        return p.x >= input[0].length || p.y >= input.length;
    }

    private boolean isFree(Vector2 p, char[][] input) {
        return input[p.y][p.x] == '.' || input[p.y][p.x] == '^';
    }
}
