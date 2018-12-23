package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.io.IOException;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 7 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/7">The Sum of Its Parts</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsParts implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        // TODO
        return null;
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
