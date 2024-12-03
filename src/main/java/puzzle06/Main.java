package puzzle06;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private boolean enabled = true;

    public static void main(String[] args) throws URISyntaxException, IOException {
        var main = new Main();
        if (args.length == 0) {
            System.out.println("Please provide an input file");
            return;
        }
        var answer = main.solve(args[1]);
        System.out.printf("Answer: %d", answer);
    }

    public long solve(String inputFile) throws URISyntaxException, IOException {
        var input = readInput(inputFile);
        return solvePuzzle(input);
    }

    private List<String> readInput(String inputFile) throws URISyntaxException, IOException, IllegalArgumentException {
        var resource = Main.class.getClassLoader().getResource(inputFile);
        if (resource == null) {
            throw new IllegalArgumentException("Input file " + inputFile + " not found");
        }
        var path = Paths.get(resource.toURI());
        return Files.readAllLines(path);
    }

    private long solvePuzzle(List<String> lines) {
        return lines.stream()
                .map(this::calculateExpression)
                .reduce(0L, Long::sum);
    }

    public long calculateExpression(String line) {
        var sum = 0L;
        var pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            var match = matcher.group(0);
            if (match.equals("don't()")) {
                enabled = false;
                continue;
            } else if (match.equals("do()")) {
                enabled = true;
                continue;
            }
            if (!enabled) {
                continue;
            }
            var a = Long.parseLong(matcher.group(1));
            var b = Long.parseLong(matcher.group(2));
            sum += a * b;
        }
        return sum;
    }

}
