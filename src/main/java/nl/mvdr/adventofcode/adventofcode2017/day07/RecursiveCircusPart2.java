package nl.mvdr.adventofcode.adventofcode2017.day07;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 7 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/7">Recursive Circus</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RecursiveCircusPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveCircusPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return weight
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Program> programs = Program.parse(lines);
        Tower tower = Tower.getTower(programs);
        
        Tower unbalancedTower = tower.findSmallestUnbalancedSubtower();
        LOGGER.debug("Unbalanced subtower: {}", unbalancedTower);
        
        // Build a map of subtower weight to the number of occurrences of each weight.
        Map<Integer, Long> weights = unbalancedTower.getSubtowers().stream()
                .mapToInt(Tower::totalWeight)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        int correctWeight = weights.entrySet().stream()
                .filter(entry -> 1L < entry.getValue().longValue())
                .map(Entry::getKey)
                .findAny()
                .get()
                .intValue();
        int incorrectWeight = weights.entrySet().stream()
                .filter(entry -> entry.getValue().longValue() == 1L)
                .map(Entry::getKey)
                .findAny()
                .get()
                .intValue();
        int weightCorrection = correctWeight - incorrectWeight;
        LOGGER.debug("Correct subtower weight: {}, incorrect subtower weight: {}, correction: {}",
                Integer.valueOf(correctWeight), Integer.valueOf(incorrectWeight), Integer.valueOf(weightCorrection));
        
        Program programWithIncorrectWeight = unbalancedTower.getSubtowers().stream()
                .filter(subtower -> subtower.totalWeight() == incorrectWeight)
                .map(Tower::getProgram)
                .findAny()
                .get();
        LOGGER.debug("Program with incorrect weight: {}", programWithIncorrectWeight);
        
        return programWithIncorrectWeight.getWeight() + weightCorrection;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RecursiveCircusPart2 instance = new RecursiveCircusPart2();

        String result = instance.solve("input-day07-2017.txt");

        LOGGER.info(result);
    }
}
