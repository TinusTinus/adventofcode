package nl.mvdr.adventofcode.adventofcode2022.day01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/1">Calorie Counting</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class CalorieCounting implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalorieCounting.class);
    
    private final int numberOfElves;
    
    /**
     * Parses the input.
     * 
     * @param lines lines from the input
     * @return elves, represented by the number of calories each elf carries
     */
    private static List<Integer> parseElves(List<String> lines) {
        List<Integer> elves = new ArrayList<>();
        
        int calories = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                elves.add(Integer.valueOf(calories));
                calories = 0;
            } else {
                calories = calories + Integer.parseInt(line);
            }
        }
        // add the final elf
        elves.add(Integer.valueOf(calories));
        
        return elves;
    }
    
    /**
     * Constructor.
     * 
     * @param numberOfElves the number of elves for whom to count the calories
     */
    public CalorieCounting(int numberOfElves) {
        super();
        this.numberOfElves = numberOfElves;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return number of calories 
     */
    @Override
    public int solve(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        List<Integer> elves = parseElves(lines);
        return solve(elves);
    }

    /**
     * Solver method.
     * 
     * @param elves elves, represented by the number of calories each elf carries
     * @return number of calories
     */
    private int solve(List<Integer> elves) {
        return elves.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .peek(calories -> LOGGER.debug("Calories: {}", Integer.valueOf(calories)))
                .limit(numberOfElves)
                .sum();
    }
}
 