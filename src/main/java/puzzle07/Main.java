package puzzle07;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    String wordToFind = "XMAS";

    @AllArgsConstructor
    static class Vector2 {
        int x, y;
    }

    class Map {
        Vector2[] path;
        boolean[][] visited;
        int w, h;

        Map(int w, int h) {
            this.w = w;
            this.h = h;

            visited = new boolean[h][w];
            Arrays.setAll(visited, i -> new boolean[w]);

            path = new Vector2[wordToFind.length()];
            Arrays.setAll(path, i -> new Vector2(0, 0));
        }

        void visit(int x, int y, int step) {
            visited[y][x] = true;
            path[step].x = x;
            path[step].y = y;
        }

        void unvisit(int x, int y) {
            visited[y][x] = false;
        }

        void printPath() {
            for (Vector2 coord : path) {
                System.out.printf("(%d,%d) ", coord.x, coord.y);
            }
            System.out.println();
        }
    }

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
        var map = new Map(w, h);
        var result = 0;
        for (var y = 0; y < map.h; y += 1) {
            for (var x = 0; x < map.w; x += 1) {
                if (input[y][x] == wordToFind.charAt(0)) {
                    result += depthFirstSearch(x, y, input, map, 1, null);
                }
            }
        }
        return result;
    }

    private int depthFirstSearch(int x, int y, char[][] input, Map map, int indexOfNextCharToFind, Vector2 direction) {
        if (indexOfNextCharToFind >= wordToFind.length()) {
            map.visit(x, y, indexOfNextCharToFind - 1);
            map.printPath();
            map.unvisit(x, y);
            return 1;
        }
        char c = wordToFind.charAt(indexOfNextCharToFind);
        int result = 0;
        map.visit(x, y, indexOfNextCharToFind - 1);
        // Check all directions
        for (int nextX = x - 1; nextX <= x + 1; nextX++) {
            for (int nextY = y - 1; nextY <= y + 1; nextY++) {
                // Actually ignore other directions than established one
                if (direction != null) {
                    if (nextX - x != direction.x || nextY - y != direction.y) {
                        continue;
                    }
                }
                // Check borders
                if (nextX == 0 && nextY == 0) continue;
                if (nextX < 0 || nextX >= map.w) continue;
                if (nextY < 0 || nextY >= map.h) continue;
                // Do not visit same cell twice
                if (map.visited[nextY][nextX]) continue;
                if (input[nextY][nextX] == c) {
                    result += depthFirstSearch(
                            nextX, nextY, input, map,
                            indexOfNextCharToFind + 1,
                            direction != null ? direction : new Vector2(nextX - x, nextY - y)
                    );
                }
            }
        }
        map.unvisit(x, y);
        return result;
    }

}
