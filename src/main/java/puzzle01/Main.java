package puzzle01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

    public int solve(String inputFile) throws URISyntaxException, IOException {
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

    private int solvePuzzle() {
        leftList.sort(Comparator.naturalOrder());
        rightList.sort(Comparator.naturalOrder());

        var totalDistance = 0;
        for (int i = 0; i < leftList.size(); i++) {
            var distance = Math.abs(leftList.get(i) - rightList.get(i));
            totalDistance += distance;
        }

        return totalDistance;
    }

}
