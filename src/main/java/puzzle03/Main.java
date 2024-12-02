package puzzle03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        var main = new Main();
        if (args.length == 0) {
            System.out.println("Please provide an input file");
            return;
        }
        var answer = main.readInputAndSolve(args[1]);
        System.out.printf("Answer: %d", answer);
    }

    public int readInputAndSolve(String inputFile) throws URISyntaxException, IOException {
        var resource = Main.class.getClassLoader().getResource(inputFile);
        assert resource != null;
        var path = Paths.get(resource.toURI());
        var scanner = new Scanner(path);

        var safeReports = 0;

        // Outer cycle: reading lines
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var innerScanner = new Scanner(line);

            // Inner cycle: reading single report
            var levelIdx = -1;
            var prevLevel = 0;
            var isAscending = false;
            var isSafe = true;

            while (innerScanner.hasNext()) {
                levelIdx += 1;
                var level = innerScanner.nextInt();
                // 1. Verify rule about diff: at least 1, at max 3
                if (levelIdx > 0) {
                    var diff = Math.abs(level - prevLevel);
                    if (diff < 1 || diff > 3) {
                        isSafe = false;
                        break;
                    }
                }
                if (levelIdx == 1) {
                    isAscending = prevLevel < level;
                } else if (levelIdx > 1) {
                    // 2. Verify rule about order
                    var isCurrentlyAscending = prevLevel < level;
                    if (isAscending != isCurrentlyAscending) {
                        isSafe = false;
                        break;
                    }
                }
                prevLevel = level;
            }
            if (isSafe) {
                safeReports += 1;
            }
        }

        scanner.close();
        return safeReports;
    }

}
