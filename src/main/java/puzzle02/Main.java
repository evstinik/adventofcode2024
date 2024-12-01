package puzzle02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

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
        readInput(inputFile);
        return solvePuzzle();
    }

    private void readInput(String inputFile) throws URISyntaxException, IOException {
        var resource = Main.class.getClassLoader().getResource(inputFile);
        assert resource != null;
        var path = Paths.get(resource.toURI());
        var scanner = new Scanner(path);

        while (scanner.hasNext()) {
            var leftItem = scanner.nextInt();
            leftList.add(leftItem);

            var rightItem = scanner.nextInt();
            rightList.add(rightItem);
        }

        scanner.close();
    }

    private long solvePuzzle() {
        long similarityScore = 0;
        var rightListCountsPerLocation = rightList
                .stream()
                .collect(
                        Collectors.groupingBy(
                                location -> location,
                                Collectors.counting()
                        )
                );
        for (int locationID: leftList) {
            similarityScore += locationID * rightListCountsPerLocation.getOrDefault(locationID, 0L);
        }
        return similarityScore;
    }

}
