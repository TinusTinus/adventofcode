package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 5 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">Alchemical Reduction</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlchemicalReductionPart2.class);
    
    @Override
    public int solve(Stream<String> lines) {
        String polymer = lines.findFirst().get();
                
        return polymer.chars()
                .map(character -> Character.toLowerCase((char)character))
                .distinct()
                .mapToObj(character -> polymer.replaceAll("" + (char)character, "").replaceAll("" + Character.toUpperCase((char)character), ""))
                .map(Polymers::react)
                .mapToInt(String::length)
                .min()
                .getAsInt();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AlchemicalReductionPart2 instance = new AlchemicalReductionPart2();

        String result = instance.solve("input-day05-2018.txt");

        LOGGER.info(result);
    }
}
