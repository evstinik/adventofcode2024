package puzzle04;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

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
        var levels = new ArrayList<Integer>();

        // Outer cycle: reading lines
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var innerScanner = new Scanner(line);

            // Inner cycle: reading single report
            levels.clear();
            while (innerScanner.hasNext()) {
                var level = innerScanner.nextInt();
                levels.add(level);
            }

            // Validating single report (slow)
            var isSafeNaive = areLevelsSafeNaive(levels);
            var isSafeBest = areLevelsSafeQuick(levels);
            if (isSafeNaive != isSafeBest) {
                System.out.println("Wrong answer");
            }
            if (isSafeBest) {
                safeReports += 1;
            }
        }

        scanner.close();
        return safeReports;
    }

    public boolean areLevelsSafeNaive(List<Integer> levels) {
        for (var idxToSkip = 0; idxToSkip < levels.size(); idxToSkip++) {
            if (areLevelsSafeSingleIteration(levels, idxToSkip)) {
                return true;
            }
        }
        return false;
    }

    private boolean areLevelsSafeSingleIteration(List<Integer> levels, int indexToSkip) {
        var isAscending = false;
        var establishedOrder = false;

        for (int levelIdx = 1; levelIdx < levels.size(); levelIdx += 1) {
            if (levelIdx == indexToSkip) continue;
            var prevLevelIdx = indexToSkip == levelIdx - 1 ? levelIdx - 2 : levelIdx - 1;
            if (prevLevelIdx < 0) continue;

            var level = levels.get(levelIdx);
            var prevLevel = levels.get(prevLevelIdx);

            // 1. Verify rule about diff: at least 1, at max 3
            var diff = Math.abs(level - prevLevel);
            if (diff < 1 || diff > 3) {
                return false;
            }
            if (!establishedOrder) {
                isAscending = prevLevel < level;
                establishedOrder = true;
            } else {
                // 2. Verify rule about order
                var isCurrentlyAscending = prevLevel < level;
                if (isAscending != isCurrentlyAscending) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean areLevelsSafeQuick(List<Integer> levels) {
        // Typical dynamic programming task
        // Go step by step, level by level and check next possible levels to make a valid longest sequence
        // To fulfill ordering criteria store at i both longest ascending sequence up to level i
        // and longest descending sequence up to level i
        class LevelState {
            int maxAsc = 1;
            int maxDesc = 1;
        }
        var state = IntStream.range(0, levels.size())
                .mapToObj(i -> new LevelState())
                .toArray(LevelState[]::new);

        // Function, that tries to match in a sequence levels at index 1 and index 2
        BiConsumer<Integer, Integer> checkLevels = ( idx1, idx2) -> {
            var diff = Math.abs(levels.get(idx1) - levels.get(idx2));
            if (1 <= diff && diff <= 3) {
                // check the order and increase corresponding state
                if (levels.get(idx1) < levels.get(idx2)) {
                    state[idx2].maxAsc = Math.max(state[idx2].maxAsc, state[idx1].maxAsc + 1);
                } else {
                    state[idx2].maxDesc = Math.max(state[idx2].maxDesc, state[idx1].maxDesc + 1);
                }
            }
        };

        int minimalToBeSafe = levels.size() - 1;
        for (int i = 0; i < levels.size(); i++) {
            if (state[i].maxAsc >= minimalToBeSafe || state[i].maxDesc >= minimalToBeSafe) {
                return true;
            }
            // Check next in line
            if (i < levels.size() - 1) {
                checkLevels.accept(i, i+1);
            }
            // Check with the skip
            if (i < levels.size() - 2) {
                checkLevels.accept(i, i+2);
            }
        }

        return false;
    }

}
