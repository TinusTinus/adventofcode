package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 5 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">Alchemical Reduction</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlchemicalReductionPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        String polymer = Files.lines(inputFilePath)
                .findFirst()
                .get();
                
        int result = polymer.chars()
                .map(character -> Character.toLowerCase((char)character))
                .distinct()
                .mapToObj(character -> polymer.replaceAll("" + (char)character, "").replaceAll("" + Character.toUpperCase((char)character), ""))
                .map(Polymers::react)
                .mapToInt(String::length)
                .min()
                .getAsInt();
        
        return Integer.valueOf(result);
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
