package puzzleXX;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle09 {
    static class NumberRule {
        Set<Integer> preceding = new HashSet<>();
        Set<Integer> following = new HashSet<>();
    }
    Map<Integer, NumberRule> rules = new HashMap<>();

    public static void main(String[] args) throws URISyntaxException, IOException, IllegalArgumentException {
        var main = new Puzzle09();
        if (args.length == 0) {
            System.out.println("Please provide an input file");
            return;
        }
        var answer = main.solve(args[1]);
        System.out.printf("Answer: %d", answer);
    }

    public int solve(String inputFile) throws URISyntaxException, IOException, IllegalArgumentException {
        var resource = Puzzle09.class.getClassLoader().getResource(inputFile);
        if (resource == null) {
            throw new IllegalArgumentException("Input file " + inputFile + " not found");
        }
        var path = Paths.get(resource.toURI());
        var scanner = new Scanner(path);

        // Read rules
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var innerScanner = new Scanner(line);
            innerScanner.useDelimiter("\\|");

            if (!innerScanner.hasNextInt()) {
                break;
            }

            var a = innerScanner.nextInt();
            innerScanner.skip("\\|");
            var b = innerScanner.nextInt();

            if (!rules.containsKey(a)) rules.put(a, new NumberRule());
            rules.get(a).following.add(b);

            if (!rules.containsKey(b)) rules.put(b, new NumberRule());
            rules.get(b).preceding.add(a);
        }

        // Read "updates"
        var sum = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var numbers = Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            var middlePage = solvePuzzle(numbers, rules);
            if (middlePage != -1) {
                sum += middlePage;
            }
        }

        return sum;
    }

    private int solvePuzzle(Integer[] numbers, Map<Integer, NumberRule> rules) {
        var numberOrder = IntStream.range(0, numbers.length)
                .boxed()
                .collect(
                        Collectors.toMap(i -> numbers[i], i -> i)
                );
        var isOk = true;
        for (var i = 0; i < numbers.length; i++) {
            var n = numbers[i];
            if (!rules.containsKey(n)) continue;
            for (var precedingN: rules.get(n).preceding) {
                if (numberOrder.containsKey(precedingN) && numberOrder.get(precedingN) > i) {
                    isOk = false;
                    break;
                }
            }
            for (var followingN: rules.get(n).following) {
                if (numberOrder.containsKey(followingN) && numberOrder.get(followingN) < i) {
                    isOk = false;
                    break;
                }
            }
        }
        if (isOk) {
            return numbers[numbers.length / 2];
        }
        return -1;
    }
}
