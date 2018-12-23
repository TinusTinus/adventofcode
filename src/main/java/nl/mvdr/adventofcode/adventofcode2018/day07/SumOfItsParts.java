package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 7 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/7">The Sum of Its Parts</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsParts implements PathSolver {

    private static final Pattern PATTERN = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.");
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        // Build a map, with the remaining steps as the key, and the set of direct unfulfilled prerequisites as the corresponding values.
        Map<Character, Set<Character>> remainingSteps = new HashMap<>();
        Files.lines(inputFilePath)
            // ignore empty lines (the last line in the file)
            .filter(Objects::nonNull)
            .filter(line -> !line.isBlank())
            .map(line -> PATTERN.matcher(line))
            .peek(Matcher::matches)
            .forEach(matcher -> {
                char prerequisite = matcher.group(1).charAt(0);
                char step = matcher.group(2).charAt(0);
                
                remainingSteps.putIfAbsent(Character.valueOf(step), new HashSet<>());
                remainingSteps.get(Character.valueOf(step)).add(Character.valueOf(prerequisite));
                remainingSteps.putIfAbsent(Character.valueOf(prerequisite), new HashSet<>());
            });
        
        String result = "";
        while (!remainingSteps.isEmpty()) {
            Character nextStep = remainingSteps.keySet().stream()
                    .filter(step -> remainingSteps.get(step).isEmpty())
                    .sorted()
                    .findFirst()
                    .get();
            result = result + nextStep;
            
            remainingSteps.remove(nextStep);
            remainingSteps.values().stream()
                    .forEach(prerequisites -> prerequisites.remove(nextStep));
        }
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SumOfItsParts instance = new SumOfItsParts();

        String result = instance.solve("input-day07-2018.txt");

        System.out.println(result);
    }
}
