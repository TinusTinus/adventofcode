package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 5 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">Alchemical Reduction</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart2 implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        String polymer = Files.lines(inputFilePath)
                .findFirst()
                .get();
                
        return "" + polymer.chars()
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

        System.out.println(result);
    }
}
